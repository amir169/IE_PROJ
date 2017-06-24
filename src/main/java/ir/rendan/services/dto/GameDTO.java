package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.rendan.model.Game;

import java.util.Date;

/**
 * Created by Amir Shams on 6/24/2017.
 */
public class GameDTO {

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String sourcePath;
    @JsonProperty
    private String conductor;
    @JsonProperty
    private int maxTeamSize;
    @JsonProperty
    private Date availableUntil;
    @JsonProperty
    private String place;
    @JsonProperty
    private int price;
    @JsonProperty
    private String logoAddress;
    @JsonProperty
    private String descriptionAddress;

    @JsonProperty
    private boolean permitted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }

    public void setMaxTeamSize(int maxTeamSize) {
        this.maxTeamSize = maxTeamSize;
    }

    public Date getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(Date availableUntil) {
        this.availableUntil = availableUntil;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLogoAddress() {
        return logoAddress;
    }

    public void setLogoAddress(String logoAddress) {
        this.logoAddress = logoAddress;
    }

    public String getDescriptionAddress() {
        return descriptionAddress;
    }

    public void setDescriptionAddress(String descriptionAddress) {
        this.descriptionAddress = descriptionAddress;
    }

    public boolean isPermitted() {
        return permitted;
    }

    public void setPermitted(boolean permitted) {
        this.permitted = permitted;
    }

    public static GameDTO loadFrom(Game game)
    {
        GameDTO result = new GameDTO();

        result.setId(game.getId());
        result.setName(game.getName());
        result.setConductor(game.getConductor());
        result.setMaxTeamSize(game.getMaxTeamSize());
        result.setLogoAddress(game.getLogoAddress());
        result.setAvailableUntil(game.getAvailableUntil());
        result.setDescriptionAddress(game.getDescriptionAddress());
        result.setSourcePath(game.getSourcePath());
        result.setPrice(game.getPrice());
        result.setPlace(game.getPlace());

        return result;
    }
}
