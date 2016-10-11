package de.teamproject16.pbft.Network;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IngridBoldt on 06.10.16.
 */
public class Receiver {
    private byte _EMPTY_RAW_BYTE = ' ';
    //private byte ANSWER_SYNTAX = 'ANSWER ';
    private byte LINE_BREAK = '\n';

    private void receiver() throws IOException {
        Boolean do_quit = false;
        while (do_quit){
            ServerSocket server = new ServerSocket();
            server.bind(); // self.s.bind((ServiceInfos().hostname, NODE_PORT))
            server.setSoTimeout(60000);
            try
            {
                Socket client = server.accept();
            }
            catch (InterruptedIOException e)
            {
                System.err.println( "Timeout nach einer Minute!" );
            }
        }
    }
}
