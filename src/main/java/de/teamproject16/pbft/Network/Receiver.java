package de.teamproject16.pbft.Network;

import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerException;
import de.teamproject16.pbft.Dockerus;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by IngridBoldt on 06.10.16.
 */
public class Receiver {
    private static byte EMPTY_RAW_BYTE = ' ';
    private static String ANSWER_SYNTAX = "ANSWER ";
    private static byte LINE_BREAK = '\n';

    public static void receiver() throws IOException, DockerCertificateException, DockerException, InterruptedException {
        Socket socket = new Socket(Dockerus.getInstance().getHostname(), 4458);
        BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte buffer[] = new byte[520];
        for(int s; (s=input.read(buffer)) != -1; )
        {
            baos.write(buffer, 0, s);
        }
        byte result[] = baos.toByteArray();
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

