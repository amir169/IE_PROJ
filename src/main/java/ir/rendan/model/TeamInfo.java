package ir.rendan.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by SalehJFZ on 11/05/2017.
 */
@Entity
@Table(name = "TEAM")
public class TeamInfo {
    @Id
    String teamname;

    @ManyToOne
    @JoinColumn(name = "USR_Name")
    UserInfo HeadUSer;

    @ManyToMany
    @JoinTable(
        name = "TM_USR",
        joinColumns = @JoinColumn(name = "TM_Name",referencedColumnName = "teamname"),
        inverseJoinColumns = @JoinColumn(name = "USR_Name",referencedColumnName = "username")
    )
    Set<UserInfo> members;

    public TeamInfo() {
    }

    public TeamInfo(String teamname, UserInfo headUSer) {
        this.teamname = teamname;
        HeadUSer = headUSer;
        this.members = new HashSet<>();
    }

    public int addMembers(Set<UserInfo> newMembers){
        members.addAll(members);
        return members.size();
    }

    public int addMember(UserInfo newMember){
        members.add(newMember);
        return members.size();
    }

    public int deleteMember(UserInfo member){
        members.remove(member);
        return members.size();
    }


}
