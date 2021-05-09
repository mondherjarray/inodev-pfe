package com.brightcoding.app.ws.entities;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class QuestionEntity implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;
    private String questionId;
    private String premier;
    private String deuxieme;
    private String troiseme;
    @ManyToOne
    private CondidatEntity condidat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getPremier() {
        return premier;
    }

    public void setPremier(String premier) {
        this.premier = premier;
    }

    public String getDeuxieme() {
        return deuxieme;
    }

    public void setDeuxieme(String deuxieme) {
        this.deuxieme = deuxieme;
    }

    public String getTroiseme() {
        return troiseme;
    }

    public void setTroiseme(String troiseme) {
        this.troiseme = troiseme;
    }

    public CondidatEntity getCondidat() {
        return condidat;
    }

    public void setCondidat(CondidatEntity condidat) {
        this.condidat = condidat;
    }
}
