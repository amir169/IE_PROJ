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

    @ManyToOne
    Game game;

    @Column
    String Name;

    @OneToMany
    public Set<Match> matches;

    @ElementCollection
    @JoinTable(name = "TM_GAME_SCORE",joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "TM_GAME_ID")
    @Column(name = "SCORES")
    public Map<Integer,Float> scores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
