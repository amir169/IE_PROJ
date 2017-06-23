package ir.rendan.model;

import javax.persistence.*;
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

    @OneToMany
    private Set<League> leagues;

    public Game(String name, String sourcePath, int leagueCount) {
        this.name = name;
        this.sourcePath = sourcePath;
        leagues = new HashSet<>();
        for (int i = 0; i < leagueCount; i++) {
            leagues.add(new League(this,"لیگ "+i) );
        }
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

    public Set<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }
}
