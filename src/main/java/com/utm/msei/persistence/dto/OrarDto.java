package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.OrarEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link OrarEntity} entity
 */
@Data
public class OrarDto implements Serializable {
    private int id;
    private DisciplinaProfesorDto idDiscProf;
    private ZiSaptamanaDto idZi;
    private ClasaDto idClasa;
    private DurataLectieDto idDurata;

    public OrarDto() {}

    public OrarDto(DisciplinaProfesorDto idDiscProf, ZiSaptamanaDto idZi, ClasaDto idClasa, DurataLectieDto idDurata) {
        this.idDiscProf=idDiscProf;
        this.idZi = idZi;
        this.idClasa = idClasa;
        this.idDurata=idDurata;
    }
}
