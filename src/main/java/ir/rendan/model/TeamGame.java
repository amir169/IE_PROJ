package ir.rendan.model;

import javax.persistence.*;
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

}
