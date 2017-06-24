package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Amir Shams on 6/24/2017.
 */
public class GameRegistrationDTO {

    @JsonProperty
    private String gameId;
    @JsonProperty
    private String teamId;


    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
