package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.DurataLectieEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link DurataLectieEntity} entity
 */
@Data
public class DurataLectieDto implements Serializable {
    private int id;
    private String durata;

    public DurataLectieDto() {}

    public DurataLectieDto(String durata) {
        this.durata = durata;
    }
}
