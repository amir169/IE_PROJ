package ir.rendan.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by SalehJFZ on 15/05/2017.
 */
@Entity
@Table
public class Code {
    @Id @GeneratedValue private int id;

    @Column
    private String address;

    @Column
    private String name;

    @Column
    private Date creationDate;

    public Code(String address) {
        this.address = address;
        this.creationDate = new Date();
    }

    public Code(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return creationDate;
    }

    public void setDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
