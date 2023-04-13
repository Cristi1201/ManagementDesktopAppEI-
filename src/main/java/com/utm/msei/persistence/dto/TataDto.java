package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.TataEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link TataEntity} entity
 */
@Data
public class TataDto implements Serializable {
    private int id;
    private String nume;
    private String prenume;
    private String telefon;

    public TataDto() {}

    public TataDto(String nume, String prenume, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.telefon = telefon;
    }
}
