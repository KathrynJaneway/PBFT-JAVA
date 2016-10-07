package de.teamproject16.pbft.Network;

import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerException;
import de.teamproject16.pbft.Dockerus;
import de.teamproject16.pbft.Messages.Message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.List;

/**
 * Created by IngridBoldt on 06.10.16.
 */
public class Sender {

    String msg;

    public void sendMessage(Message msg) throws UnsupportedEncodingException, InterruptedException, DockerException, DockerCertificateException {
        broadcast(msg.toString());
    }

    public void broadcast(String message) throws UnsupportedEncodingException, DockerCertificateException, DockerException, InterruptedException {
        List<String> otherHostnames = Dockerus.getInstance().getHostnames(true);
        message += "\n";
        msg = "ANSWER " + message.length() + "\n" + message;
        byte[] msgBytes = msg.getBytes("UTF-8");
        for (String nodeHost: otherHostnames){
            Boolean sent = false;
            while(!sent){
                try {
                    Socket socket = new Socket(nodeHost, 4458);  //SENDEN EINER TCP STRING NACHRICHT

                    //socket.
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
