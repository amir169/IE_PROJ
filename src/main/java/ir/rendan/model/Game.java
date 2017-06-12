package ir.rendan.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by SalehJFZ on 15/05/2017.
 */
@Entity
@Table
public class Game {
    @Id @GeneratedValue int id;

    @Column
    String name;

    @Column
    String sourcePath;

    @OneToMany
    Set<League> leagues;

    public Game(String name, String sourcePath, int leagueCount) {
        this.name = name;
        this.sourcePath = sourcePath;
        leagues = new HashSet<>();
        for (int i = 0; i < leagueCount; i++) {
            leagues.add(new League(this,"لیگ "+i) );
        }
    }
}
