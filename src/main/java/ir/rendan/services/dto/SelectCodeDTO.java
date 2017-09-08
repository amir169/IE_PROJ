package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Amir Shams on 9/8/2017.
 */
public class SelectCodeDTO {
    @JsonProperty
    private int code;

    @JsonProperty
    private int game;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }
}
