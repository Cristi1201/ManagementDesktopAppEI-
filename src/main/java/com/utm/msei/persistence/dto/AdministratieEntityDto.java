package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.AdministratieEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link AdministratieEntity} entity
 */
@Data
public class AdministratieEntityDto implements Serializable {
    private final int id;
    private final String nume;
    private final String prenume;
    private final String idnp;
    private final String telefon;
    private final String status;
    private final byte[] poza;
}
