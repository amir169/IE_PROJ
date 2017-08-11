package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.rendan.model.League;
import ir.rendan.model.TeamGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by SalehJFZ on 11/08/2017.
 */
public class LeagueLightDTO {
    @JsonProperty
    private String name;

    @JsonProperty
    private long id;

    @JsonProperty
    private boolean deleted;

    @JsonProperty
    List<TeamGame> teamGameList;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TeamGame> getTeamGameList() {
        return teamGameList;
    }

    public void setTeamGameList(List<TeamGame> teamGameList) {
        this.teamGameList = teamGameList;
    }
/*
    public static LeagueLightDTO loadFrom(League league){
        LeagueLightDTO dto = new LeagueLightDTO();
        dto.setId(league.getId());
        dto.setName(league.getName());
        //todo deleted


    }*/
}
