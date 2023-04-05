package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.AdministratieEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * A DTO for the {@link AdministratieEntity} entity
 */
@Data
@Component
public class AdministratieDto implements Serializable {
    private int id;
    private UserDto idUser;

    public AdministratieDto() {}

    public AdministratieDto(UserDto idUser) {
        this.idUser = idUser;
    }

    public AdministratieDto(int id, UserDto idUser) {
        this.id = id;
        this.idUser = idUser;
    }
}
