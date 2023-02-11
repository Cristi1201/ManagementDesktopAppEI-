package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.DurataLectieEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link DurataLectieEntity} entity
 */
@Data
public class DurataLectieEntityDto implements Serializable {
    private final int id;
    private final String durata;
}
