package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ElevEntity;
import com.utm.msei.persistence.entity.ParintiEntity;
import com.utm.msei.persistence.entity.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link ElevEntity} entity
 */
@Data
public class ElevDto implements Serializable {
    private int id;
    private Integer idClasa;
    private ParintiEntity idParinti;
    private UserEntity idUser;

    public ElevDto() {}

    public ElevDto(Integer idClasa, ParintiEntity idParinti, UserEntity idUser) {
        this.idClasa = idClasa;
        this.idParinti = idParinti;
        this.idUser = idUser;
    }

    public ElevDto(int id, Integer idClasa, ParintiEntity idParinti, UserEntity idUser) {
        this.id = id;
        this.idClasa = idClasa;
        this.idParinti = idParinti;
        this.idUser = idUser;
    }
}
