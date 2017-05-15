package ir.rendan.model;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by SalehJFZ on 15/05/2017.
 */
@Entity
@Table
public class League {
    @Id @GeneratedValue int id;

    @Column
    String Name;

    @OneToMany
    Set<Match> played;

    @OneToMany
    Set<Match> toPlay;

    @OneToMany
    Map<TeamGame,Float> scores;
}
