package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.dto.enums.EntityTypeEnum;
import com.utm.msei.persistence.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link UserEntity} entity
 */
public class UserDto implements Serializable {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String nume;
    @Getter
    @Setter
    private String prenume;
    @Getter
    @Setter
    private String email;
//    @Getter
    @Setter
    private List<EntityTypeEnum> userType;
    @Getter
    @Setter
    private String telefon;
    @Getter
    @Setter
    private String idnp;
    @Getter
    @Setter
    private LocalDate dataNastere;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private byte[] poza;

    public UserDto() {}

    public UserDto(String email, String password, List<EntityTypeEnum> userType, String nume, String prenume, String idnp, String telefon, LocalDate dataNastere, byte[] poza) {
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

    public UserDto(int id, String email, String password, List<EntityTypeEnum> userType, String nume, String prenume, String idnp, String telefon, LocalDate dataNastere, byte[] poza) {
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

    public List<EntityTypeEnum> getUserType() {
        List<EntityTypeEnum> userTypes = new ArrayList<>();
        for (String en : this.userType.toString().split(",")) {
            en = en.trim();
            if (en.startsWith("[")) {
                en = en.toString().substring(1);
            }
            if (en.endsWith("]")) {
                en = en.substring(0, en.length() - 1);
            }
            en = en.toUpperCase();
            userTypes.add(EntityTypeEnum.valueOf(en));
        }
        return userTypes;
    }
}
