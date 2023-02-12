package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.UserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link UserEntity} entity
 */
@Data
public class UserDto implements Serializable {
    private int id;
    private String email;
    private String password;
    private String userType;
    private AdministratieDto idAdmin;
    private ProfesorDto idProfesor;
    private ElevDto idElev;

    public UserDto() {}

    public UserDto(String email, String password, String userType, AdministratieDto idAdmin, ProfesorDto idProfesor, ElevDto idElev) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.idAdmin = idAdmin;
        this.idProfesor = idProfesor;
        this.idElev = idElev;
    }
}
