package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ClasaEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ClasaEntity} entity
 */
@Data
public class ClasaDto implements Serializable {
    private int id;
    private ProfesorDto profesorDto;
    private String nume;

    public ClasaDto() {}

    public ClasaDto(String nume) {
        this.nume = nume;
    }
}
