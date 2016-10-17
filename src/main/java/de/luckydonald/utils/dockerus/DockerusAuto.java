package de.luckydonald.utils.dockerus;

import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerException;

/**
 * Created by IngridBoldt on 17.10.16.
 */
public class DockerusAuto extends Dockerus {
    DockerusAuto() throws DockerCertificateException {
        //
    }
    private static Dockerus instance;

    static public Dockerus getInstance() {
        if (DockerusAuto.instance == null) {
            try {
                DockerusAuto.instance = Dockerus.getInstance();
                DockerusAuto.instance.me();
            } catch (DockerCertificateException | InterruptedException | DockerException e) {
                try {
                    DockerusAuto.instance = DockerusDummy.getInstance();
                } catch (DockerCertificateException e1) {
                    System.err.println("LOL THIS SHOULDN'T FAIL BECAUSE FAILING IS NOT IMPLEMENTED!!!111");
                    e1.printStackTrace();
                }
            }
        }
        return DockerusAuto.instance;
    }

}
