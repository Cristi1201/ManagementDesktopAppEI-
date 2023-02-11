package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.DisciplinaEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link DisciplinaEntity} entity
 */
@Data
public class DisciplinaEntityDto implements Serializable {
    private final int id;
    private final String disciplina;
}
