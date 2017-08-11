package ir.rendan.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by SalehJFZ on 15/05/2017.
 */
@Entity
@Table(name="leagues")
public class League {
    @Id @GeneratedValue private int id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column
    private String name;

    @OneToMany
    private Set<Match> matches;

    @Column(nullable = false)
    private boolean deleted = false;


    @ElementCollection
    @JoinTable(name = "TM_GAME_SCORE",joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "TM_GAME_ID")
    @Column(name = "SCORES")
    private Map<TeamGame,Double> scores;

    public League(Game game, String name) {
        this.game = game;
        this.name = name;
        this.matches = new HashSet<>();
        this.scores = new HashMap<>();
        this.deleted = false;
    }

    public League() {
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public void addTeamGame(TeamGame teamGame, double initScore){
        scores.put(teamGame,initScore);
    }

    public Double removeTeamGame(TeamGame teamGame){
        return scores.remove(teamGame);
    }

    public Map<TeamGame, Double> getScores() {
        return scores;
    }
}
