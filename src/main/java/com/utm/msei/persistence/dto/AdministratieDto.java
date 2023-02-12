package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.AdministratieEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link AdministratieEntity} entity
 */
@Data
public class AdministratieDto implements Serializable {
    private int id;
    private String nume;
    private String prenume;
    private String idnp;
    private String telefon;
    private String status;
    private byte[] poza;

    public AdministratieDto() {}

    public AdministratieDto(String nume, String prenume, String idnp, String telefon, String status, byte[] poza) {
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.telefon = telefon;
        this.status = status;
        this.poza = poza;
    }
}
