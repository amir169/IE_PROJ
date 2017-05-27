package ir.rendan.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Amir Shams on 5/26/2017.
 */
@Component
public class ConstantReader {

    @Value("${server.address}")
    private String serverAddress;

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.mail.username}")
    private String emailAddress;


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }
}
