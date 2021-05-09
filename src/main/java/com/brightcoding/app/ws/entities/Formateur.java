package com.brightcoding.app.ws.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "formateur")
public class Formateur extends UserEntity implements Serializable {

    @Column(nullable=true, length=50)
    private String lastName;
    @Column(nullable=true, length=8)
    private int phone;

    @Column(nullable=true, length=50)
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }


}
