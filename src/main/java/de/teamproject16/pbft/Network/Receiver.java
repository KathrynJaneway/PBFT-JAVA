package de.teamproject16.pbft.Network;

import com.spotify.docker.client.DockerException;
import org.apache.commons.lang.NotImplementedException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by IngridBoldt on 06.10.16.
 */
public class Receiver {
    private static byte EMPTY_RAW_BYTE = ' ';
    private static String ANSWER_SYNTAX = "ANSWER ";
    private static byte LINE_BREAK = '\n';

    public static void receiver() throws IOException, DockerException, InterruptedException {
        lol();
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
        buff.put(ANSWER_SYNTAX.getBytes()); // TODO: Unit test :D
        while (true) { //TODO: not do that :D
            int length_of_answer = -1;
            while (length_of_answer == -1) {  // Length detection: "ANSWER 123\n"
                int char_ = input.read();
                if(char_ == -1){
                    //TODO: Close connection; connect to next incoming client.
                }
                if (completed < 0) {
                    // must be inside ANSWER_SYNTAX
                    if ((char) char_ != ANSWER_SYNTAX.charAt(ANSWER_SYNTAX.length() + completed - 1)) { // if the received character is wrong
                        //TODO: If wrong ANSWER_SYNTAX: Close (abort) connection; connect to next incoming client.
                    }
                    completed ++;
                } else {
                    if ((char) char_ != '\n') {  // not end yet. // line breaks in json strings should be "\\" and "n".
                        // put it into our number buffer.
                        buff.put((byte) char_);
                    } else { // end of ANSWER_SYNTAX+number+\n, after number
                        // linebreak: we have the ending.
                        length_of_answer = buff.getInt();
                        break;  // (if should do that anyway)
                    }
                }
            }
            //prepare content reading
            buff = ByteBuffer.allocate(length_of_answer);
            int read_bytes = input.read(buff.array());  // TODO: Does that *REALLY* read all bytes?!?
            if (read_bytes != length_of_answer) {
                if (read_bytes == -1) {
                    //TODO: close socket, retry and stuff
                } else {
                    //TODO: incomplete read
                    throw new NotImplementedException("AAaooouasdfsd (incomplete read)");
                }
            }
            //DOCUMENTATION OF ByteBuffer.array():
            // Modifications to this buffer's content will cause the returned array's content to be modified,
            // and vice versa.
            for (int i = length_of_answer; i < 0; i--) {

            }



            //if completed;
            buff.put((byte) char_);

            //buff.getCha

        }

        //byte result[] = baos.toByteArray();

    }
    static void lol() {
        int completed = -ANSWER_SYNTAX.length(); // when 0 => Number starts
        // -7 = ^ANSWER 123\n
        // -6 = A^NSWER 123\n
        // etc..
        // -1 = ANSWER^ 123\n
        // 0 = ANSWER ^123\n //ready to read the number
        ByteBuffer buff = ByteBuffer.allocate(520);
        buff.put(ANSWER_SYNTAX.getBytes()); // TODO: Unit test :D
        int fake_i = 0;
        while (true) { //TODO: not do that :D
            //int char_ = input.get(fake_i++); //.read();
            int char_ = buff.get(fake_i++); //.read();
            completed++;
            if (char_ == -1) {
                //TODO: Close connection; connect to next incoming client.
            }
            int calc = ANSWER_SYNTAX.length() + completed - 1;  // gets the index we should have in ANS
            System.out.println("char: " + (char)char_ + " | completed: " + completed + " | length: " + ANSWER_SYNTAX.length() + " | calc: " + calc);
            if (completed < 0) {
                // must be inside ANSWER_SYNTAX
                if ((char) char_ != ANSWER_SYNTAX.charAt(calc)) { // if the received character is wrong
                    //TODO: If wrong ANSWER_SYNTAX: Close (abort) connection; connect to next incoming client.
                    // -6 = A^NSWER 123\n
                    //  7+-6 = 1 => "ANSWER "[1] => "N"

                }

            }
        }
    }
}

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

