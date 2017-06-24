package ir.rendan.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by SalehJFZ on 15/05/2017.
 */

@Entity
@Table(name = "team_games")
public class TeamGame {
    @Id @GeneratedValue private int id;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany
    private Set<Code> codes;

    @OneToOne
    private Code selectedCode;

    public TeamGame(){}

    public TeamGame(Team team, Game game) {
        this.team = team;
        this.game = game;
        this.codes = new HashSet<>();
        this.selectedCode = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<Code> getCodes() {
        return codes;
    }

    public void setCodes(Set<Code> codes) {
        this.codes = codes;
    }

    public Code getSelectedCode() {
        return selectedCode;
    }

    public void setSelectedCode(Code selectedCode) {
        this.selectedCode = selectedCode;
    }
}

