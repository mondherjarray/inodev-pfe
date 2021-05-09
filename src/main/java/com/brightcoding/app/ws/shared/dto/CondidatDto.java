package com.brightcoding.app.ws.shared.dto;

import com.brightcoding.app.ws.entities.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CondidatDto extends UserDto implements Serializable {
    private static final long serialVersionUID = -2624881664878912922L;

    private String firstName;

    private String phone;
    private String photo;
    private String lastName;
    private String gender;
    private Date date_birthday;
    private String nationality;
    private List<EducationDto> education;

    public List<EducationDto> getEducation() {
        return education;
    }
    private List<ExperienceEntity> experience;
    private List<SkillsEntity> skills;
    private List<OriginEntity> source;
    private List<QuestionEntity> question;
    private List<DocumentEntity> document;
    public void setEducation(List<EducationDto> education) {
        this.education = education;
    }



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

    public List<ExperienceEntity> getExperience() {
        return experience;
    }

    public void setExperience(List<ExperienceEntity> experience) {
        this.experience = experience;
    }

    public List<SkillsEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsEntity> skills) {
        this.skills = skills;
    }

    public List<OriginEntity> getSource() {
        return source;
    }

    public void setSource(List<OriginEntity> source) {
        this.source = source;
    }

    public List<QuestionEntity> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionEntity> question) {
        this.question = question;
    }

    public List<DocumentEntity> getDocument() {
        return document;
    }

    public void setDocument(List<DocumentEntity> document) {
        this.document = document;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}

