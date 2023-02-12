package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ProfesorEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ProfesorEntity} entity
 */
@Data
public class ProfesorDto implements Serializable {
    private int id;
    private String nume;
    private String prenume;
    private String idnp;
    private String telefon;
    private String gradDidactic;
    private byte[] poza;

    public ProfesorDto() {}

    public ProfesorDto(String nume, String prenume, String idnp, String telefon, String gradDidactic, byte[] poza) {
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.telefon = telefon;
        this.gradDidactic = gradDidactic;
        this.poza = poza;
    }
}
