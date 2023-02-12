package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ParintiEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ParintiEntity} entity
 */
@Data
public class ParintiDto implements Serializable {
    private int id;
    private String tataNume;
    private String tataPrenume;
    private String tataTelefon;
    private String mamaNume;
    private String mamaPrenume;
    private String mamaTelefon;

    public ParintiDto() {}

    public ParintiDto(String tataNume, String tataPrenume, String tataTelefon, String mamaNume, String mamaPrenume, String mamaTelefon) {
        this.tataNume = tataNume;
        this.tataPrenume = tataPrenume;
        this.tataTelefon = tataTelefon;
        this.mamaNume = mamaNume;
        this.mamaPrenume = mamaPrenume;
        this.mamaTelefon = mamaTelefon;
    }
}
