package ir.rendan.model;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by SalehJFZ on 15/05/2017.
 */
@Entity
@Table(name="matches")
public class Match {
    @Id @GeneratedValue private int id;

    @Column
    private short played;

    @Column
    private long date;//the date fo running

    @Column
    private short seen;//become true after changing league score based on this match results

    @Column
    private short visible;//become false after league results reset(recommended)


    @ElementCollection
    @JoinTable(name = "MATCH_SCORE",joinColumns = @JoinColumn(name = "MATCH_ID"))
    @MapKeyColumn(name = "TM_GAME_ID")
    @Column(name = "SCORES")
    private Map<Integer,Double> scores;

    @ManyToOne
    private League leagueOwner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getPlayed() {
        return played;
    }

    public void setPlayed(short played) {
        this.played = played;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public short getSeen() {
        return seen;
    }

    public void setSeen(short seen) {
        this.seen = seen;
    }

    public short getVisible() {
        return visible;
    }

    public void setVisible(short visible) {
        this.visible = visible;
    }

    public Map<Integer, Double> getScores() {
        return scores;
    }

    public void setScores(Map<Integer, Double> scores) {
        this.scores = scores;
    }

    public League getLeagueOwner() {
        return leagueOwner;
    }

    public void setLeagueOwner(League leagueOwner) {
        this.leagueOwner = leagueOwner;
    }
}
