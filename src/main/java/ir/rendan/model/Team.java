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
    String teamname;

    @ManyToOne
    @JoinColumn(name = "USR_Name")
    User HeadUSer;

    @ManyToMany
    @JoinTable(
        name = "TM_USR",
        joinColumns = @JoinColumn(name = "TM_Name",referencedColumnName = "teamname"),
        inverseJoinColumns = @JoinColumn(name = "USR_Name",referencedColumnName = "username")
    )
    Set<User> members;

    public Team() {
    }

    public Team(String teamname, User headUSer) {
        this.teamname = teamname;
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
