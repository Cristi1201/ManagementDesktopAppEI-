package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.DisciplinaProfesorEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link DisciplinaProfesorEntity} entity
 */
@Data
public class DisciplinaProfesorEntityDto implements Serializable {
    private final int id;
    private final ProfesorEntityDto idProfesor;
    private final DisciplinaEntityDto idDisciplina;
}
