package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ProfesorEntity;
import com.utm.msei.persistence.entity.UserEntity;
import com.utm.msei.persistence.mapper.UserMapper;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A DTO for the {@link ProfesorEntity} entity
 */
@Data
public class ProfesorDto implements Serializable {
    private int id;
    private UserDto idUser;

    public ProfesorDto() {}

    public ProfesorDto(UserDto idUser) {
        this.idUser = idUser;
    }

    public ProfesorDto(int id, UserDto idUser) {
        this.id = id;
        this.idUser = idUser;
    }
}
