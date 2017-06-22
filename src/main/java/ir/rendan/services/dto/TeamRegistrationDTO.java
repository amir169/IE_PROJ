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
    @JsonProperty
    private List<String> members;
    @JsonProperty
    private WhoAmIDTO manager;

    public WhoAmIDTO getManager() {
        return manager;
    }

    public void setManager(WhoAmIDTO manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
