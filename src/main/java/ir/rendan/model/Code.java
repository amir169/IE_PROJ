package ir.rendan.model;

import javax.persistence.*;

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
    private long date; // date added

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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
