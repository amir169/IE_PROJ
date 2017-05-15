package ir.rendan.model;

import javax.persistence.*;
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
}
