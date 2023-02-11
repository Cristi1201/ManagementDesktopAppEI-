package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.AbsenteEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link AbsenteEntity} entity
 */
@Data
public class AbsenteEntityDto implements Serializable {
    private final int id;
    private final ElevEntityDto idElev;
    private final OrarEntityDto idLectie;
    private final String mN;
    private final Date data;
}
