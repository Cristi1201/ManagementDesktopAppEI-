package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ParintiEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ParintiEntity} entity
 */
@Data
public class ParintiEntityDto implements Serializable {
    private final int id;
    private final String tataNume;
    private final String tataPrenume;
    private final String tataTelefon;
    private final String mamaNume;
    private final String mamaPrenume;
    private final String mamaTelefon;
}
