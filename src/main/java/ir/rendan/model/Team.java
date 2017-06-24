package ir.rendan.model;


import ir.rendan.util.StringGenerator;

import javax.persistence.*;
import java.util.Date;
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
    private short validated; //becomes true after all member accept invitation.

    @Column
    private Date registrationDate;

    @Column
    private String code;

    @ManyToOne
    @JoinColumn(name = "manager_id")
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getValidated() {
        return validated;
    }

    public void setValidated(short validated) {
        this.validated = validated;
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

    public Team(String name, User manager, Set<User> invitedMembers,Set<User> members) {
        this.name = name;
        this.manager = manager;
        this.members = members;
        this.invitedMembers = invitedMembers;
        this.validated = new Short("0");
        this.registrationDate = new Date();
        this.code = StringGenerator.generateValidationCode();
    }

    public void validate()
    {
        if(invitedMembers.isEmpty())
            validated = new Short("1");
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<User> getInvitedMembers() {
        return invitedMembers;
    }

    public void setInvitedMembers(Set<User> invitedMembers) {
        this.invitedMembers = invitedMembers;
    }
}
