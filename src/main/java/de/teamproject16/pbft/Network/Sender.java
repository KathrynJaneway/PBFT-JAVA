package de.teamproject16.pbft.Network;

import de.teamproject16.pbft.Dockerus;
import de.teamproject16.pbft.Messages.Message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * Created by IngridBoldt on 06.10.16.
 */
public class Sender {

    String msg;

    public void sendMessage(Message msg) throws UnsupportedEncodingException {
        broadcast(msg.toString());
    }

    public void broadcast(String message) throws UnsupportedEncodingException {
        String[] otherHostnames = new Dockerus().other_hostnames(true);
        message += "\n";
        msg = "ANSWER " + message.length() + "\n" + message;
        byte[] msgBytes = msg.getBytes("UTF-8");
        for (String nodeHost: otherHostnames){
            Boolean sent = false;
            while(!sent){
                try {
                    Socket socket = new Socket(nodeHost, 4458);//SENDEN EINER TCP STRING NACHRICHT

                    socket.
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
