package de.teamproject16.pbft.Network;

/**
 * Created by luckydonald on 17.10.16.
 */
public class CloseConnectionPlease extends Exception {
    public CloseConnectionPlease(String msg) {
        super(msg);
    }
    public CloseConnectionPlease() {
        super();
    }
}
