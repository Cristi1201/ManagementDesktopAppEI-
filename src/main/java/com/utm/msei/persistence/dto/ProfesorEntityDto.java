package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ProfesorEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ProfesorEntity} entity
 */
@Data
public class ProfesorEntityDto implements Serializable {
    private final int id;
    private final String nume;
    private final String prenume;
    private final String idnp;
    private final String telefon;
    private final String gradDidactic;
    private final byte[] poza;
}
