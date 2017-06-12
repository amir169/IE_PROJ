package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;

/**
 * Created by SalehJFZ on 13/06/2017.
 */
public class TeamRegistrationDTO {
    private Map<String,String> regDetails;

    @JsonAnyGetter
    public Map<String,String> getRegDetails(){
        return  regDetails;
    }
}
