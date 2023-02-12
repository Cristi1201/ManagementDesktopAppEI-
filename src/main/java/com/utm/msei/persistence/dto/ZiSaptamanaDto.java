package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ZiSaptamanaEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ZiSaptamanaEntity} entity
 */
@Data
public class ZiSaptamanaDto implements Serializable {

    private int id;
    private String zi;

    public ZiSaptamanaDto() {}

    public ZiSaptamanaDto(String zi) {
        this.zi = zi;
    }

    public ZiSaptamanaDto(int id, String zi) {
        this.id = id;
        this.zi = zi;
    }
}
