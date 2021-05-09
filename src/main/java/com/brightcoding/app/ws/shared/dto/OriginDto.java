package com.brightcoding.app.ws.shared.dto;



public class OriginDto {
    private Integer id;
    private String sourceId;
    private String position;
    private String describ;
    private CondidatDto condidat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public CondidatDto getCondidat() {
        return condidat;
    }

    public void setCondidat(CondidatDto condidat) {
        this.condidat = condidat;
    }
}
