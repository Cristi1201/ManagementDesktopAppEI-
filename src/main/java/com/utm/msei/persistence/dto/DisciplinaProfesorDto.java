package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.DisciplinaProfesorEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link DisciplinaProfesorEntity} entity
 */
@Data
public class DisciplinaProfesorDto implements Serializable {
    private int id;
    private ProfesorDto idProfesor;
    private DisciplinaDto idDisciplina;

    public DisciplinaProfesorDto() {}

    public DisciplinaProfesorDto(ProfesorDto idProfesor, DisciplinaDto idDisciplina) {
        this.idProfesor = idProfesor;
        this.idDisciplina = idDisciplina;
    }
}
