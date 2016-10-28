package de.teamproject16.pbft;

import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerException;
import de.teamproject16.pbft.Messages.InitMessage;
import de.teamproject16.pbft.Network.MessageQueue;
import de.teamproject16.pbft.Network.Sender;

import java.io.UnsupportedEncodingException;

import static de.luckydonald.utils.dockerus.DockerusAuto.getInstance;

/**
 * Created by IngridBoldt on 26.10.16.
 */
public class NormalCase {
    int sequencelength = 1000;

    int leader = 1;

    /**
     * The row 15 to 27 from the algorithm.
     * @throws DockerException
     * @throws InterruptedException
     * @throws UnsupportedEncodingException
     * @throws DockerCertificateException
     */
    public void normalFunction() throws DockerException, InterruptedException, UnsupportedEncodingException, DockerCertificateException {
        Sender sender = new Sender();
        sender.sendMessage(new InitMessage(((int) System.currentTimeMillis()/sequencelength), getInstance().getNumber(), ToDO.getSensorValue()));//UNixtimestampe

        if(this.leader == getInstance().getNumber()){
            if(MessageQueue.initM.size() >= getInstance().getHostnames(true).size() - (1/3)){
                CancelableLinkedBlockingQueue initStore = MessageQueue.initM;
                float median = (float) Median.calculateMedian(initStore, initStore.size()); //Gives us the head of the queue, with poll we have a timeout. Possibly we must change it for LeaderChange.

            }
            //notify soll auf timeout oder genügend nachrichten warten

        }
    }
}
