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
        if (message instanceof InitMessage){
            try {
                initM.put(message);
            } catch (InterruptedException e) {
                System.out.println("Message can't put in the queue (InitMessage).");
                e.printStackTrace();
            }
        }
        if (message instanceof LeaderChangeMessage){
            try {
                leaderChangeM.put(message);
            } catch (InterruptedException e) {
                System.out.println("Message can't put in the queue (LeaderChangeMessage).");
                e.printStackTrace();
            }
        }
        if (message instanceof PrevoteMessage){
            try {
                prevoteM.put(message);
            } catch (InterruptedException e) {
                System.out.println("Message can't put in the queue (PrevoteMessage).");
                e.printStackTrace();
            }
        }
        if (message instanceof ProposeMessage){
            try {
                proposeM.put(message);
            } catch (InterruptedException e) {
                System.out.println("Message can't put in the queue (ProposeMessage).");
                e.printStackTrace();
            }
        }
        if (message instanceof VoteMessage){
            try {
                voteM.put(message);
            } catch (InterruptedException e) {
                System.out.println("Message can't put in the queue (VoteMessage).");
                e.printStackTrace();
            }
        }
    }

}
