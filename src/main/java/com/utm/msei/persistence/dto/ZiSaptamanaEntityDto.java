package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ZiSaptamanaEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ZiSaptamanaEntity} entity
 */
@Data
public class ZiSaptamanaEntityDto implements Serializable {
//    private final int id;
    private final String zi;
}
