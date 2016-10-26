package de.luckydonald.utils.dockerus;

import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DockerException;
import com.spotify.docker.client.messages.Container;
import org.apache.commons.lang.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class DockerusDummy extends Dockerus{

    private DockerusDummy() throws DockerCertificateException {
        System.out.println("Project probably not loaded in docker-compose...");
    }

    static private DockerusDummy instance = null;

    static public DockerusDummy getInstance() throws DockerCertificateException {
        if (DockerusDummy.instance == null) {
            DockerusDummy.instance = new DockerusDummy();
        }
        return DockerusDummy.instance;
    }

    @Override
    public List<String> getHostnames(Boolean excludeSelf) throws DockerException, InterruptedException {
        List<String> foo = new ArrayList<>();
        foo.add("localhost");
        return foo;
    }

    @Override
    public String getHostname() throws DockerException, InterruptedException {
        return "localhost";
    }

    @Override
    DockerClient getCLI() {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    String getEnvHostname() {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    Container me() throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    String getId() throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    String getService() throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    String getService(Container container) throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    String getName() throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    String getName(Container container) throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    String getProject() throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    String getProject(Container container) throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    int getNumber() throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    int getNumber(Container container) throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }

    @Override
    List<Container> getContainers(boolean excludeSelf) throws DockerException, InterruptedException {
        throw new NotImplementedException("Project probably not loaded in docker-compose...");
    }
}
