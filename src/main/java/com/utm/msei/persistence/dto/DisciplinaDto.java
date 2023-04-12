package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.DisciplinaEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link DisciplinaEntity} entity
 */
@Data
public class DisciplinaDto implements Serializable {
    private int id;
    private String disciplina;

    public DisciplinaDto() {}

    public DisciplinaDto(String disciplina) {
        this.disciplina = disciplina;
    }
    public DisciplinaDto(int id, String disciplina) {
        this.id = id;
        this.disciplina = disciplina;
    }
}
