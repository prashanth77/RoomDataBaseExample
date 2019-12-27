package com.winitsolutions.todolist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "first_name")
    private String firstname;

    @ColumnInfo(name = "last_name")
    private String lastname;

    private String college;

    private String phone_numbe;

    public User() {
    }

    public User(String firstname, String lastname, String college, String phone_numbe) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.college = college;
        this.phone_numbe=phone_numbe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;


    }

    public String getPhone_numbe() {
        return phone_numbe;
    }

    public void setPhone_numbe(String phone_numbe) {
        this.phone_numbe = phone_numbe;
    }
}
