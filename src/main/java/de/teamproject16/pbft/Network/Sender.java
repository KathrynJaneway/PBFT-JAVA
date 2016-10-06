package de.teamproject16.pbft.Network;

import de.teamproject16.pbft.Messages.Message;

/**
 * Created by IngridBoldt on 06.10.16.
 */
public class Sender {

    String msg;

    public void sendMessage(Message msg){
        broadcast(msg.toString());
    }

    public void broadcast(String message){
        message += "\n";
        msg = "ANSWER " + message.length() + "\n" + message;
    }
}
