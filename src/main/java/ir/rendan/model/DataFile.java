package ir.rendan.model;

import ir.rendan.util.StringGenerator;

import javax.persistence.*;

/**
 * Created by Amir Shams on 8/2/2017.
 */
@Entity
@Table(name="data_files")
public class DataFile {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String key;

    @Column
    private String address;

    public DataFile() {
    }

    public DataFile(String name, String address) {
        this.name = name;
        this.key = StringGenerator.generateValidationCode();

        this.address = address + key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
