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
    long date;// the date fo running

    @OneToMany//must test
    Map<Team,Float> scores;


}
