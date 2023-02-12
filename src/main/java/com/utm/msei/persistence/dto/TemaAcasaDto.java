package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.TemaAcasaEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link TemaAcasaEntity} entity
 */
@Data
public class TemaAcasaDto implements Serializable {
    private final int id;
    private final ElevDto idElev;
    private final byte[] lucrare;
    private final ActivitateDto idActivitate;
}
