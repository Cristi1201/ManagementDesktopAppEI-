package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.AbsenteEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link AbsenteEntity} entity
 */
@Data
public class AbsenteDto implements Serializable {
    private int id;
    private ElevDto idElev;
    private OrarDto idLectie;
    private String mN;
    private Date data;

    public AbsenteDto() {}

    public AbsenteDto(ElevDto idElev, OrarDto idLectie, String mN, Date data) {
        this.idElev = idElev;
        this.idLectie = idLectie;
        this.mN = mN;
        this.data = data;
    }
}
