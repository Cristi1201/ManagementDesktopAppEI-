package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.MamaEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link MamaEntity} entity
 */
@Data
public class MamaDto implements Serializable {
    private int id;
    private String nume;
    private String prenume;
    private String telefon;

    public MamaDto() {}

    public MamaDto(String nume, String prenume, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.telefon = telefon;
    }
}
