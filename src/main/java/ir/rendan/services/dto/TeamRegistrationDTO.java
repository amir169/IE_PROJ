package ir.rendan.services.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import ir.rendan.model.User;

import java.util.List;

/**
 * Created by SalehJFZ on 13/06/2017.
 */
public class TeamRegistrationDTO {

    @JsonProperty
    private String name;
    @JsonProperty("members_array")
    private List<User> members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
