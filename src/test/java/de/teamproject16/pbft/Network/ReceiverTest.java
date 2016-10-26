package de.teamproject16.pbft.Network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.tools.java.SyntaxError;

import java.io.IOException;
import java.net.*;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.ServerSocketChannel;

import static org.junit.Assert.*;
import de.luckydonald.utils.mockups.ServerSocketMockup;

/**
 * Created by  on
 *
 * @author luckydonald
 * @since 26.10.2016
 **/
public class ReceiverTest {
    Receiver r = null;
    ServerSocketMockup s = null;

    @Before
    public void setUp() throws Exception {
        this.s = new ServerSocketMockup();
        this.r = new Receiver();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testReceiver() throws Exception {
        r.receiver(this.s);
    }

    @Test
    public void testAddMessage() throws Exception {

    }
}