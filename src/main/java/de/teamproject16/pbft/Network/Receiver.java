package de.teamproject16.pbft.Network;

import com.spotify.docker.client.DockerException;
import de.luckydonald.utils.ObjectWithLogger;
import org.apache.commons.lang.NotImplementedException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by IngridBoldt on 06.10.16.
 */
public class Receiver extends ObjectWithLogger {
    private static String ANSWER_SYNTAX = "ANSWER ";
    private static byte LINE_BREAK = '\n';

    public void receiver() throws IOException, DockerException, InterruptedException {
        ServerSocket server = new ServerSocket(4458);
        Socket socket = server.accept();
        // Socket socket = new Socket();
        // socket.bind(new InetSocketAddress(DockerusAuto.getInstance().getHostname(), 4458));
        BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // byte buffer[] = new byte[520];

        int completed = -ANSWER_SYNTAX.length(); // when 0 => Number starts
        // -7 = ^ANSWER 123\n
        // -6 = A^NSWER 123\n
        // etc..
        // -1 = ANSWER^ 123\n
        // 0 = ANSWER ^123\n //ready to read the number
        ByteBuffer buff = ByteBuffer.allocate(520);
        // buff.put(ANSWER_SYNTAX.getBytes()); // TODO: Unit test :D
        while (true) { //TODO: not do that :D
            long length_of_answer = -1;
            while (length_of_answer == -1) {  // Length detection: "ANSWER 123\n"
                int char_ = input.read();
                if(char_ == -1){
                    //TODO: Close connection; connect to next incoming client.
                    throw new NotImplementedException("-1");
                }
                int calc = ANSWER_SYNTAX.length() + completed;  // gets the index we should have in ANS
                System.out.println("char: " + (char)char_ + " | completed: " + completed + " | length: " + ANSWER_SYNTAX.length() + " | calc: " + calc);
                if (completed < 0) {
                    // must be inside ANSWER_SYNTAX
                    if ((char) char_ != ANSWER_SYNTAX.charAt(ANSWER_SYNTAX.length() + completed)) { // if the received character is wrong
                        //TODO: If wrong ANSWER_SYNTAX: Close (abort) connection; connect to next incoming client.
                        throw new NotImplementedException("!= ANSWER");
                    }
                    completed ++;
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
                        String str = new String(str_bytes, Charset.forName("UTF-8"));  // http://stackoverflow.com/a/17355227
                        this.getLogger().warning("Did Read: " + buff + "> " + str);
                        length_of_answer = Integer.parseInt(str);
                        break;  // (if should do that anyway)
                    }
                }
            }
            this.getLogger().finest("Waiting to receive " + length_of_answer + " bytes.");
            //prepare content reading
            buff = ByteBuffer.allocate((int) length_of_answer);
            int bytes_read = 0;
            while (bytes_read < length_of_answer) {
                int char_ = input.read();
                if (char_ == -1) {
                    //TODO: close socket, retry and stuff
                    throw new NotImplementedException("-1");
                }
                bytes_read++;
                buff.put((byte)char_);
                //TODO: last char should be '\n'
            }
            String result = buff.asCharBuffer().toString();
            this.getLogger().warning("Received: " + result);
            this.addMessage(result);
        }

        //byte result[] = baos.toByteArray();

    }
    private void addMessage(String json) {
        //TODO: string to json
        //TODO: json to Message instance
        //TODO: put message in a list
    }
}
// {"hello": "world"}
//18+\n = 19

/**        Boolean do_quit = false;

        while (!do_quit){
            new ServerSocket(4456);
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress(Dockerus.getInstance().getHostname(), 4458);
            socket.bind(addr);
            socket.setSoTimeout(60000);
            byte answer = EMPTY_RAW_BYTE;
            int completed = -1;
            while ((!do_quit) && socket.isClosed()){
                byte[] buffer = new byte[520];
                InputStream rein = socket.getInputStream();
                BufferedReader b = new BufferedReader(new InputStreamReader(rein));
                System.out.println("Hallo receiver. " + rein.available());

                socket.getInputStream().read(buffer);
                String text = null;
                if (buffer.length == 0){
                    socket.close();
                }
                if (completed == 0){
                    if (answer != LINE_BREAK){
                        System.out.println("Error Receiver");
                        break;
                    }
                    if (buffer[0] == EMPTY_RAW_BYTE){
                        completed -= 1;
                        continue;
                    }
                    text = buffer.toString();
                    if (text.length() > 0 && text.equals("")){
                        addMessage(text);
                    }
                    answer = EMPTY_RAW_BYTE;
                    buffer = null;
                    continue;
                }
                if (completed < -1 && buffer.length != ANSWER_SYNTAX.length()){
                    throw new IOException();
                }
                if (completed <= -1 && text.startsWith(ANSWER_SYNTAX) && text.endsWith(String.valueOf(LINE_BREAK))){
                    completed = text.length() - ANSWER_SYNTAX.length() - 1;// -1 wegen Zeilenumbruch
                    buffer = null;
                }
            }
        }
    }

    public static Object addMessage(String text){
        return null;
    }**/

