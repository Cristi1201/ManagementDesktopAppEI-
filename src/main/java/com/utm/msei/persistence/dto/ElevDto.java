package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ElevEntity;
import com.utm.msei.persistence.entity.MamaEntity;
import com.utm.msei.persistence.entity.TataEntity;
import com.utm.msei.persistence.entity.UserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ElevEntity} entity
 */
@Data
public class ElevDto implements Serializable {
    private int id;
    private Integer idClasa;
    private TataDto idTata;
    private MamaDto idMama;
    private UserDto idUser;

    public ElevDto() {}
    public ElevDto(UserDto idUser) {
        this.idUser = idUser;
    }
    public ElevDto(Integer idClasa, TataDto idTata, MamaDto idMama, UserDto idUser) {
        this.idClasa = idClasa;
        this.idTata = idTata;
        this.idMama = idMama;
        this.idUser = idUser;
    }

    public ElevDto(int id, Integer idClasa, TataDto idTata, MamaDto idMama, UserDto idUser) {
        this.id = id;
        this.idClasa = idClasa;
        this.idTata = idTata;
        this.idMama = idMama;
        this.idUser = idUser;
    }
}
