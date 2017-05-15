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
    long date;// the date fo running

    @ElementCollection
    @JoinTable(name = "MATCH_SCORE",joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "TM_GAME_ID")
    @Column(name = "SCORES")
    Map<Integer,Float> scores;

    @ManyToOne
    public League leagueOwner;


}
