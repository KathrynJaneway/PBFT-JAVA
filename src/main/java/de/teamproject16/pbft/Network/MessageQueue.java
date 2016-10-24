package de.teamproject16.pbft.Network;

import de.teamproject16.pbft.Messages.*;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IngridBoldt on 19.10.16.
 */
public class MessageQueue {
    static LinkedBlockingQueue leaderChangeM = new LinkedBlockingQueue<>(); // Maximumgröße von Int + initial Elemente
    static LinkedBlockingQueue initM = new LinkedBlockingQueue(); //Maximumgröße von Int
    static LinkedBlockingQueue prevoteM = new LinkedBlockingQueue();
    static LinkedBlockingQueue proposeM = new LinkedBlockingQueue();
    static LinkedBlockingQueue voteM = new LinkedBlockingQueue();

    public static void messageQueue(Message message){
        try {
            if (message instanceof InitMessage){
                initM.put(message);
            }
            if (message instanceof LeaderChangeMessage) {
                leaderChangeM.put(message);
            }
            if (message instanceof PrevoteMessage) {
                prevoteM.put(message);
            }
            if (message instanceof ProposeMessage){
                proposeM.put(message);
            }
            if (message instanceof VoteMessage){
                voteM.put(message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


