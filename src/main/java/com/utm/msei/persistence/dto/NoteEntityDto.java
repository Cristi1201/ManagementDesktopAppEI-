package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.NoteEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link NoteEntity} entity
 */
@Data
public class NoteEntityDto implements Serializable {
    private final int id;
    private final ElevEntityDto idElev;
    private final OrarEntityDto idLectie;
    private final int nota;
    private final Date data;
}
