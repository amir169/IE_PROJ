package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.rendan.model.User;

/**
 * Created by Amir Shams on 6/21/2017.
 */
//TODO: must be removed after user model completed
public class WhoAmIDTO {

    @JsonProperty
    private String name;

    @JsonProperty
    private String username;

    @JsonProperty
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static WhoAmIDTO loadFrom(User user)
    {
        WhoAmIDTO dto = new WhoAmIDTO();
        dto.setName(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());

        return dto;
    }
}
