package com.brightcoding.app.ws.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "condidat")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CondidatEntity extends UserEntity implements Serializable {
    private static final long serialVersionUID = -5763827745308343856L;

    @Column(nullable=true, length=50)
    private String firstName;

    @Column(nullable=true, length=50)
    private String phone;
    @Column(nullable=true, length=50)
    private String lastName;

    @Column(nullable=true, length=50)
    private String gender;
    @Temporal(TemporalType.DATE)
    @Column(nullable=true)
    private Date date_birthday;
    @Column(nullable=true, length=50)
    private String nationality;
    @Column(nullable=true)
    private String photo;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="condidat" )
    private List<EducationEntity> education;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="condidat" )
    private List<ExperienceEntity> experience;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="condidat" )
    private List<SkillsEntity> skills;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="condidat" )
    List<CondidatOffreEntity> condidatoffre;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    @OneToMany(cascade = CascadeType.ALL,mappedBy="condidat" )
    private List<OriginEntity> source;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="condidat" )
    private List<QuestionEntity> question;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="condidat" )
    private List<DocumentEntity> document;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<EducationEntity> getEducation() {
        return education;
    }

    public void setEducation(List<EducationEntity> education) {
        this.education = education;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    @Override
    public String toString() {
        return "Condidat [firstName=" + firstName + ", photo=" + photo + ", phone=" + phone + ", lastName=" + lastName + ", date_birthday=" + date_birthday
                + ", gender=" + gender + ", nationality=" + nationality + ", email=" + getEmail()+ ", userId=" + getUserId() + "]";
    }

}
