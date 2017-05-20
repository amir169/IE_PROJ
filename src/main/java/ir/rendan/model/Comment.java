package ir.rendan.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ABM on 5/18/2017.
 */
@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue
    int id;

    @Column
    String question;

    @Column
    String ans;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    UserInfo user;



    public Comment(String question, String ans, UserInfo user) {
        this.question = question;
        this.ans = ans;
        this.user = user;
    }
}
