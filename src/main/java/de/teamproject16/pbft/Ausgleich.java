package de.teamproject16.pbft;

import java.util.*;

/**
 * Created by IngridBoldt on 29.09.16.
 */
public class Ausgleich {
    static ArrayList value = new ArrayList();

    public static Object calculateMedian() {
        Collections.sort(value);
        int calculate = value.size()/2;
        Object median = value.get(calculate);
        return median;
    }

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
}
