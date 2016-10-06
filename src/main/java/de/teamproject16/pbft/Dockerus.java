package de.teamproject16.pbft;

/**
 * Created by IngridBoldt on 06.10.16.
 */
public class Dockerus {
    String LABEL_COMPOSE_CONTAINER_NUMBER = "com.docker.compose.container-number";
    String LABEL_COMPOSE_PROJECT = "com.docker.compose.project";
    String LABEL_COMPOSE_SERVICE = "com.docker.compose.service";
    //CACHING_TIME = timedelta(seconds=60);

    public String[] other_hostnames(Boolean excludeSelf){
        String[] hostnameList = new String[3];
        hostnameList[0] = "teamproject_java_1";
        hostnameList[1] = "teamproject_java_2";
        hostnameList[2] = "teamproject_java_3";
        hostnameList[3] = "teamproject_java_4";
        return hostnameList;
    }
}
