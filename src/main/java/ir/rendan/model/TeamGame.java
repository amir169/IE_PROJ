package ir.rendan.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by SalehJFZ on 15/05/2017.
 */

@Entity
@Table(name = "TEAM_GAME")
public class TeamGame {
    @Id @GeneratedValue int id;

    @ManyToOne
    Team team;

    @ManyToOne
    Game game;

    @OneToMany
    Set<Code> codes;

    @OneToOne
    Code selectedCode;

    public TeamGame(Team team, Game game) {
        this.team = team;
        this.game = game;
        codes = new HashSet<>();
        selectedCode = null;
    }

    public Team getTeam() {
        return team;
    }

    public Game getGame() {
        return game;
    }

    public Set<Code> getCodes() {
        return codes;
    }

    public Code getSelectedCode() {
        return selectedCode;
    }

    public void setSelectedCode(Code selectedCode) {
        this.selectedCode = selectedCode;
    }

    public void addCode(Code code){
        codes.add(code);
    }

    public boolean removeCode(Code code){
        return codes.remove(code);
    }
}

