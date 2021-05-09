package com.brightcoding.app.ws.responses;

import com.brightcoding.app.ws.entities.*;

import java.util.Date;
import java.util.List;

public class CondidatResponse extends UserResponse{
    private String firstName;


    private String phone;
    private String lastName;
    private String photo;
    private String gender;
    private Date date_birthday;
    private String nationality;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate_birthday() {
        return date_birthday;
    }

    public void setDate_birthday(Date date_birthday) {
        this.date_birthday = date_birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
