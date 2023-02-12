package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ElevEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link ElevEntity} entity
 */
@Data
public class ElevDto implements Serializable {
    private int id;
    private String nume;
    private String prenume;
    private String idnp;
    private Date dataNastere;
    private ClasaDto idClasa;
    private ParintiDto idParinti;
    private String telefon;
    private byte[] poza;

    public ElevDto() {}

    public ElevDto(String nume, String prenume, String idnp, Date dataNastere, ClasaDto idClasa, ParintiDto idParinti, String telefon, byte[] poza) {
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.dataNastere = dataNastere;
        this.idClasa = idClasa;
        this.idParinti = idParinti;
        this.telefon = telefon;
        this.poza = poza;
    }
}
