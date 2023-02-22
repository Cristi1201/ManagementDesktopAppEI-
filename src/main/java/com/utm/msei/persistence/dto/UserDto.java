package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.dto.enums.EntityTypeEnum;
import com.utm.msei.persistence.entity.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link UserEntity} entity
 */
@Data
public class UserDto implements Serializable {
    private int id;
    private String email;
    private String password;
    private EntityTypeEnum userType;
    private String nume;
    private String prenume;
    private String idnp;
    private String telefon;
    private Date dataNastere;
    private byte[] poza;

    public UserDto() {}

    public UserDto(String email, String password, EntityTypeEnum userType, String nume, String prenume, String idnp, String telefon, Date dataNastere, byte[] poza) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.telefon = telefon;
        this.dataNastere = dataNastere;
        this.poza = poza;
    }

    public UserDto(int id, String email, String password, EntityTypeEnum userType, String nume, String prenume, String idnp, String telefon, Date dataNastere, byte[] poza) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.telefon = telefon;
        this.dataNastere = dataNastere;
        this.poza = poza;
    }
}
