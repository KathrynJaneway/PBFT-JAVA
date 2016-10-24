package de.teamproject16.pbft.Network;

import de.luckydonald.utils.ObjectWithLogger;
import de.teamproject16.pbft.Messages.Message;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* Test with netcat in terminal:
 * $ nc localhost 4458
 * ANSWER 19
 * {"hello": "world"}
 */


/**
 * Class to which will receive Messages.
 * It will call {@link Receiver#addMessage(String) this.addMessage(String messageContent)} to process incoming messages.
 **/
public class Receiver extends ObjectWithLogger {
    private static String ANSWER_SYNTAX = "ANSWER ";
    private static byte LINE_BREAK = '\n';

    public void receiver() throws IOException {
        ServerSocket server = new ServerSocket(4458); // throws IOException
        while (true) {  // For each connection do  // TODO: self.do_quit or similar
            Socket socket = server.accept();  // throws IOException
            try {
                BufferedInputStream input = new BufferedInputStream(socket.getInputStream()); // throws IOException

                int completed = -ANSWER_SYNTAX.length();
                /**
                 -7 = ^ANSWER 123\n
                 -6 = A^NSWER 123\n
                 -1 = ANSWER^ 123\n
                 0 = ANSWER ^123\n  => ready to read the number
                 **/

                ByteBuffer buff = null;
                // buff.put(ANSWER_SYNTAX.getBytes()); // TODO: Unit test :D
                long length_of_answer = -1;
                buff = ByteBuffer.allocate(520);  // ANSWER <int>\n
                while (length_of_answer == -1) {  // Length detection: "ANSWER 123\n"
                    int char_ = input.read();  // throws IOException
                    if (char_ == -1) {
                        // Client disconnected prematurely: Close connection; connect to next incoming client.
                        throw new CloseConnectionPlease("Client disconnected.");
                    }
                    if (completed < 0) {
                        // must be inside ANSWER_SYNTAX
                        if ((char) char_ != ANSWER_SYNTAX.charAt(ANSWER_SYNTAX.length() + completed)) {
                            // if the received character not as expected of the ANSWER_SYNTAX header.
                            //Wrong ANSWER_SYNTAX => Close (abort) connection; connect to next incoming client.
                            throw new CloseConnectionPlease("Syntax error in ANSWER header.");
                        }
                        completed++;
                    } else {
                        if ((char) char_ != '\n') {  // not end yet. // line breaks in json strings should be "\\" and "n".
                            // put it into our number buffer.
                            buff.put((byte) char_);
                        } else { // end of ANSWER_SYNTAX+number+\n, after number
                            // linebreak: we have the ending.
                            // http://stackoverflow.com/a/22717246
                            byte[] str_bytes = new byte[buff.position()];
                            buff.rewind();
                            buff.get(str_bytes);
                            // http://stackoverflow.com/a/17355227
                            String str = new String(str_bytes, Charset.forName("UTF-8"));
                            this.getLogger().warning("Did Read: " + buff + "> " + str);
                            length_of_answer = Integer.parseInt(str);
                            break;  // (while should end anyway)
                        }
                    }
                }
                // prepare content reading
                this.getLogger().finest("Waiting to receive " + length_of_answer + " bytes.");
                buff = ByteBuffer.allocate((int) length_of_answer);
                int bytes_read = 0;
                while (bytes_read < length_of_answer) {
                    int char_ = input.read();  // throws IOException
                    if (char_ == -1) {
                        // Client disconnected prematurely: Close connection; connect to next incoming client.
                        throw new CloseConnectionPlease("Client disconnected.");
                    }
                    if (bytes_read+1 != length_of_answer) {
                        if (char_ == '\n') {
                            // last char should be '\n'
                            getLogger().finest("Skipping ending linebreak.");
                        } else {
                            getLogger().warning("Message did not end with '\\n', ignoring!");
                            throw new CloseConnectionPlease("Not ending with '\\n'");
                        }
                    }
                    bytes_read++;
                    buff.put((byte) char_);
                }
                String result = new String(buff.array(), Charset.forName("UTF-8"));
                this.getLogger().fine("Received: " + result);
                this.addMessage(result);
                // now the client would close the sockets. We do, too, in the finally statement
            } catch (CloseConnectionPlease e) {
                this.getLogger().finest("Requested to close connection prematurely: " + e.getLocalizedMessage());
            } catch (IOException e){
                this.getLogger().warning("IOException");
                e.printStackTrace();
            } finally {
                // in case it should close prematurely (by throwing CloseConnectionPlease)
                // in case of normal operation after a received message.
                try {
                    socket.close();  // throws IOException
                } catch (IOException e) {
                    this.getLogger().warning("Ignored failed socket closing.");
                }
            }
        }
    }

    private void addMessage(String json) {
        try {
            JSONObject data = new JSONObject(json);
            MessageQueue.messageQueue(Message.messageConvert(data));
        } catch (JSONException e) {
            System.out.println("Convert the String to JSONObject failed.");
            e.printStackTrace();
        }
        //Listen für die einzelnen Messages
        //Threadsave  in python classe message queue
        //einer lock zugriff und zählen

        //TODO: string to json
        //TODO: json to Message instance
        //TODO: put message in the fitting list
    }
}
