package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ClasaEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ClasaEntity} entity
 */
@Data
public class ClasaEntityDto implements Serializable {
    private final int id;
    private final String nume;
}
