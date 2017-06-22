package ir.rendan.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by SalehJFZ on 11/05/2017.
 */
@Entity
@Table(name = "teams")
public class Team {
    @Id
    private String name;

    @Column
    private short authorized; //becomes true after all member accept invitation.

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User manager;

    @ManyToMany
    @JoinTable(
        name = "team_user",
        joinColumns = @JoinColumn(name = "team_name",referencedColumnName = "name"),
        inverseJoinColumns = @JoinColumn(name = "username",referencedColumnName = "username")
    )
    private Set<User> members;

    @ManyToMany
    @JoinTable(
            name = "team_invitation",
            joinColumns = @JoinColumn(name = "team_name",referencedColumnName = "name"),
            inverseJoinColumns = @JoinColumn(name = "username",referencedColumnName = "username")
    )
    private Set<User> invitedMembers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAuthorized() {
        return authorized;
    }

    public void setAuthorized(short authorized) {
        this.authorized = authorized;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public Team() {
    }

    public Team(String name, User manager) {
        this.name = name;
        this.manager = manager;
        this.members = new HashSet<>();
        this.authorized = new Short("0");
    }

    public void addMembers(Set<User> newMembers){
        this.members = newMembers;
    }

    public void addMember(User newMember){
        members.add(newMember);
    }

    public void deleteMember(User member){
        members.remove(member);
    }


}
