package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.UserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link UserEntity} entity
 */
@Data
public class UserEntityDto implements Serializable {
    private final int id;
    private final String email;
    private final String password;
    private final String userType;
    private final AdministratieEntityDto idAdmin;
    private final ProfesorEntityDto idProfesor;
    private final ElevEntityDto idElev;
}
