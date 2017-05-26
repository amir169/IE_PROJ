package ir.rendan.model;

import javax.persistence.*;

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
    User user;



    public Comment(String question, String ans, User user) {
        this.question = question;
        this.ans = ans;
        this.user = user;
    }
}
