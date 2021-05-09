package com.brightcoding.app.ws.entities;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class OriginEntity implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;
    private String sourceId;
    private String position;
    private String describ;
    @ManyToOne
    private CondidatEntity condidat;

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

    public CondidatEntity getCondidat() {
        return condidat;
    }

    public void setCondidat(CondidatEntity condidat) {
        this.condidat = condidat;
    }
}
