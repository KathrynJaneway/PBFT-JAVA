package de.teamproject16.pbft;

import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerException;
import de.luckydonald.utils.dockerus.DockerusAuto;
import de.teamproject16.pbft.Messages.InitMessage;
import de.teamproject16.pbft.Network.Sender;

import java.io.UnsupportedEncodingException;

/**
 * Created by IngridBoldt on 26.10.16.
 */
public class NormalCase {
    int sequencelength = 1000;

    int leader = 1;

    public void normalFunction() throws DockerException, InterruptedException, UnsupportedEncodingException, DockerCertificateException {
        Sender sender = new Sender();
        sender.sendMessage(new InitMessage(((int) System.currentTimeMillis()/sequencelength), DockerusAuto.getInstance().getNumber(), ToDO.getSensorValue()));//UNixtimestampe

        if(this.leader == DockerusAuto.getInstance().getNumber()){
            //notify soll auf timeout oder genügend nachrichten warten

        }
    }
}
