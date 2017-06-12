package ir.rendan.model;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by SalehJFZ on 15/05/2017.
 */
@Entity
@Table
public class Match {
    @Id @GeneratedValue int id;

    @Column
    short played;

    @Column
    long date;//the date fo running

    @Column
    short seen;//become true after changing league score based on this match results

    @Column
    short visible;//become false after league results reset(recommended)


    @ElementCollection
    @JoinTable(name = "MATCH_SCORE",joinColumns = @JoinColumn(name = "MATCH_ID"))
    @MapKeyColumn(name = "TM_GAME_ID")
    @Column(name = "SCORES")
    Map<Integer,Double> scores;

    @ManyToOne
    public League leagueOwner;


}
