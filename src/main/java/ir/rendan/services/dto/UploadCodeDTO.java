package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Amir Shams on 8/2/2017.
 */
public class UploadCodeDTO {

    @JsonProperty
    private String code;

    @JsonProperty
    private int game;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }
}
