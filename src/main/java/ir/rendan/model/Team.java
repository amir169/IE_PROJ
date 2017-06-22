package ir.rendan.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by SalehJFZ on 11/05/2017.
 */
@Entity
@Table(name = "TEAM")
public class Team {
    @Id
    private String name;

    @Column
    private short authorized; //becomes true after all member accept invitation.

    @ManyToOne
    @JoinColumn(name = "USR_Name")
    private User HeadUSer;

    @ManyToMany
    @JoinTable(
        name = "TM_USR",
        joinColumns = @JoinColumn(name = "TM_Name",referencedColumnName = "teamname"),
        inverseJoinColumns = @JoinColumn(name = "USR_Name",referencedColumnName = "username")
    )
    Set<User> members;//TODO this must be changed to map<User,short> wich save each member accept it's invitation or not

    public Team() {
    }

    public Team(String teamname, User headUSer) {
        this.name = teamname;
        HeadUSer = headUSer;
        this.members = new HashSet<>();
    }

    public int addMembers(Set<User> newMembers){
        members.addAll(members);
        return members.size();
    }

    public int addMember(User newMember){
        members.add(newMember);
        return members.size();
    }

    public int deleteMember(User member){
        members.remove(member);
        return members.size();
    }


}
