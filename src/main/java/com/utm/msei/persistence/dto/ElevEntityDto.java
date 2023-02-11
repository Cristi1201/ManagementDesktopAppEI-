package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ElevEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link ElevEntity} entity
 */
@Data
public class ElevEntityDto implements Serializable {
    private final int id;
    private final String nume;
    private final String prenume;
    private final String idnp;
    private final Date dataNastere;
    private final ClasaEntityDto idClasa;
    private final ParintiEntityDto idParinti;
    private final String telefon;
    private final byte[] poza;
}
