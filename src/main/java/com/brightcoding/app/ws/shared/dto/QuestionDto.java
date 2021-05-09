package com.brightcoding.app.ws.shared.dto;

import com.brightcoding.app.ws.entities.CondidatEntity;


public class QuestionDto {
    private Integer id;
    private String questionId;
    private String premier;
    private String deuxieme;
    private String troiseme;
    private CondidatDto condidat;

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

    public CondidatDto getCondidat() {
        return condidat;
    }

    public void setCondidat(CondidatDto condidat) {
        this.condidat = condidat;
    }
}
