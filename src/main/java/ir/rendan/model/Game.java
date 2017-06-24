package ir.rendan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by SalehJFZ on 15/05/2017.
 */
@Entity
@Table(name="games")
public class Game {
    @Id @GeneratedValue private int id;

    @Column
    private String name;

    @Column
    private String sourcePath;

    @Column
    private String conductor;

    @Column
    private int maxTeamSize;

    @Column
    private Date availableUntil;

    @Column
    private String place;

    @Column
    private int price;

    @Column
    private String logoAddress;

    @Column
    private String descriptionAddress;

//    @OneToMany
//    @JsonIgnore
//    private Set<League> leagues;

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

    public Game()
    {}

    public Game(String name, String sourcePath, int leagueCount) {
        this.name = name;
        this.sourcePath = sourcePath;
//        leagues = new HashSet<>();
//        for (int i = 0; i < leagueCount; i++) {
//            leagues.add(new League(this,"League " + i + 1));
//        }
    }

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

//    public Set<League> getLeagues() {
//        return leagues;
//    }
//
//    public void setLeagues(Set<League> leagues) {
//        this.leagues = leagues;
//    }
}
