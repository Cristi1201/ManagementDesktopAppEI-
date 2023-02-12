package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.NoteEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link NoteEntity} entity
 */
@Data
public class NoteDto implements Serializable {
    private int id;
    private ElevDto idElev;
    private OrarDto idLectie;
    private int nota;
    private Date data;

    public NoteDto() {}

    public NoteDto(ElevDto idElev, OrarDto idLectie, int nota, Date data) {
        this.idElev = idElev;
        this.idLectie = idLectie;
        this.nota = nota;
        this.data = data;
    }
}
