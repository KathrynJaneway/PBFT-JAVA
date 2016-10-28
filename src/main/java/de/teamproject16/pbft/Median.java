package de.teamproject16.pbft;

import de.teamproject16.pbft.Messages.InitMessage;
import de.teamproject16.pbft.Messages.Message;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IngridBoldt on 29.09.16.
 */
public class Median {

    public static Object calculateMedian(CancelableLinkedBlockingQueue initM, int l) throws InterruptedException {
        List<Float> initStore = new LinkedList<>();
        Number seqInit = (((Message) initM.peek()).sequence_no);
        for (int i = 0; i<=l; i++){
            if ((((Message) initM.peek()).sequence_no) == seqInit) {
                initStore.add((Float) ((InitMessage) initM.take()).value);
            }
        }
        Collections.sort(initStore);
        int calculate = initStore.size()/2;
        Object median = initStore.get(calculate);
        return median;
    }
/*
    public static void simulate(int count){
        for(int i = 0; i<count; i++){
            double v = Math.random()*10;
            value.add(v);
        }
    }

    public static void main (String[] args){
        simulate(4);
        System.out.println(value);
        System.out.println(calculateMedian());
    }
    */
}
