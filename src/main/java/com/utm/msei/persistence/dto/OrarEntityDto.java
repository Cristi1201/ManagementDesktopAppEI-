package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.OrarEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link OrarEntity} entity
 */
@Data
public class OrarEntityDto implements Serializable {
    private final int id;
    private final DisciplinaProfesorEntityDto idDiscProf;
    private final ZiSaptamanaEntityDto idZi;
    private final ClasaEntityDto idClasa;
    private final DurataLectieEntityDto idDurata;

    public OrarEntityDto(int id, DisciplinaProfesorEntityDto idDiscProf, ZiSaptamanaEntityDto idZi, ClasaEntityDto idClasa, DurataLectieEntityDto idDurata) {
        this.id = id;
        this.idDiscProf=idDiscProf;
        this.idZi = idZi;
        this.idClasa = idClasa;
        this.idDurata=idDurata;
    }
}
