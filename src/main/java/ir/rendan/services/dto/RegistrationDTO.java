package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Amir Shams on 5/20/2017.
 */
public class RegistrationDTO {

    @JsonProperty
    private String username;
    @JsonProperty
    private String password;
    @JsonProperty
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
