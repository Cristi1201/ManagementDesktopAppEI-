package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ActivitateEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link ActivitateEntity} entity
 */
@Data
public class ActivitateEntityDto implements Serializable {
    private final int id;
    private final OrarEntityDto idOrar;
    private final Date data;
    private final String subiect;
    private final String tema;
}
