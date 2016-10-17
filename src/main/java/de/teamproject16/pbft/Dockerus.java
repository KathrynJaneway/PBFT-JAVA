package de.teamproject16.pbft;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DockerException;
import com.spotify.docker.client.messages.Container;


import java.util.List;
import java.util.stream.Collectors;

// import static de.luckydonald.utils.lambdas.LambdaUtils.uncheckCall;

/**
 * @author luckydonald
 */
public class Dockerus {
    String LABEL_COMPOSE_CONTAINER_NUMBER = "com.docker.compose.container-number";
    String LABEL_COMPOSE_PROJECT = "com.docker.compose.project";
    String LABEL_COMPOSE_SERVICE = "com.docker.compose.service";

    final DockerClient docker;

    //CACHING_TIME = timedelta(seconds=60);

    static private Dockerus instance = null;

    static public Dockerus getInstance() throws DockerCertificateException {
        if (Dockerus.instance == null) {
            Dockerus.instance = new Dockerus();
        }
        return Dockerus.instance;
    }

    private Dockerus() throws DockerCertificateException {
        // Create a client based on DOCKER_HOST and DOCKER_CERT_PATH env vars
        docker = DefaultDockerClient.fromEnv().build();
        // docker.listContainers(DockerClient.ListContainersParam.withLabel(""))
    }


    DockerClient getCLI() {
        return docker;
    }

    String getEnvHostname() {
        return System.getenv("HOSTNAME");
    }

    Container me() throws DockerException, InterruptedException {
        return this.getCLI().listContainers().stream().filter(this::filterIsIdEqualHostname).limit(1).collect(Collectors.toList()).get(0);
    }

    String getId() throws DockerException, InterruptedException {
        //TODO int
        return this.me().id();
    }

    String getService() throws DockerException, InterruptedException {
        return this.getService(this.me());
    }
    String getService(Container container) throws DockerException, InterruptedException {
        return container.labels().get(this.LABEL_COMPOSE_SERVICE);
    }

    String getName() throws DockerException, InterruptedException {
        return this.getService();
    }
    String getName(Container container) throws DockerException, InterruptedException {
        return this.getService(container);
    }

    String getProject() throws DockerException, InterruptedException {
        return this.getProject(this.me());
    }
    String getProject(Container container) throws DockerException, InterruptedException {
        System.out.print(container.labels());
        return container.labels().get(this.LABEL_COMPOSE_PROJECT);

    }


    String getNumber() throws DockerException, InterruptedException {
        return this.getNumber(this.me());
    }
    String getNumber(Container container) throws DockerException, InterruptedException {
        //TODO: int
        return container.labels().get(this.LABEL_COMPOSE_CONTAINER_NUMBER);
    }


    List<Container> getContainers(boolean excludeSelf) throws DockerException, InterruptedException {
        return this.getCLI().listContainers(
                DockerClient.ListContainersParam.withLabel(this.LABEL_COMPOSE_PROJECT, this.getProject()),
                DockerClient.ListContainersParam.withLabel(this.LABEL_COMPOSE_SERVICE, this.getService())
        ).stream().filter(c -> !excludeSelf || this.filterIsIdEqualHostname(c)).collect(Collectors.toList());
    }

    public String getHostname() throws DockerException, InterruptedException {
        return this.getHostname(this.me());
    }
    String getHostname(Container container) throws DockerException, InterruptedException {
        return "" + this.getProject(container) + "_" + this.getService(container) + "_" + this.getNumber(container);
    }

    public List<String> getHostnames(Boolean excludeSelf) throws DockerException, InterruptedException {
        return this.getContainers(excludeSelf).stream().map(c -> { try { return this.getHostname(c); } catch (InterruptedException | DockerException e) { e.printStackTrace(); return null; } }).filter(c -> c != null).collect(Collectors.toList());
        // #java_sucks  http://stackoverflow.com/a/19757456/3423324
    }

    boolean filterIsIdEqualHostname(Container c) {
        return c.id().substring(0, 12).equals(this.getEnvHostname().substring(0, 12));
    }
}
