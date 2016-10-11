package de.teamproject16.pbft.Network;

import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerException;
import de.teamproject16.pbft.Dockerus;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IngridBoldt on 06.10.16.
 */
public class Receiver {
    private byte EMPTY_RAW_BYTE = ' ';
    private String ANSWER_SYNTAX = "ANSWER ";
    private byte LINE_BREAK = '\n';

    private void receiver() throws IOException, DockerCertificateException, DockerException, InterruptedException {
        Boolean do_quit = false;

        while (!do_quit){
            ServerSocket server = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress(Dockerus.getInstance().getHostname(), 4458);
            server.bind(addr);
            server.setSoTimeout(60000);
            try
            {
                Socket client = server.accept();
            }
            catch (InterruptedIOException e)
            {
                System.err.println( "Timeout nach einer Minute! (Receiver)" );
            }
            byte answer = EMPTY_RAW_BYTE;
            int completed = -1;
            while ((!do_quit) && server.isClosed()){

            }
        }
    }
}
