package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.TemaAcasaEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link TemaAcasaEntity} entity
 */
@Data
public class TemaAcasaEntityDto implements Serializable {
    private final int id;
    private final ElevEntityDto idElev;
    private final byte[] lucrare;
    private final ActivitateEntityDto idActivitate;
}
