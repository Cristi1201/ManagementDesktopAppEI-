package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ActivitateEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link ActivitateEntity} entity
 */
@Data
public class ActivitateDto implements Serializable {
    private int id;
    private OrarDto idOrar;
    private Date data;
    private String subiect;
    private String tema;

    public ActivitateDto() {}

    public ActivitateDto(OrarDto idOrar, Date data, String subiect, String tema) {
        this.idOrar = idOrar;
        this.data = data;
        this.subiect = subiect;
        this.tema = tema;
    }
}
