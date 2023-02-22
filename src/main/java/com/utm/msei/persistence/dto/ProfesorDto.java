package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.ProfesorEntity;
import com.utm.msei.persistence.entity.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A DTO for the {@link ProfesorEntity} entity
 */
@Data
public class ProfesorDto implements Serializable {
    private int id;
    private String gradDidactic;  // TODO to review
    private UserEntity idUser;

    public ProfesorDto() {}

    public ProfesorDto(String gradDidactic, UserEntity idUser) {
        this.gradDidactic = gradDidactic;
        this.idUser = idUser;
    }

    public ProfesorDto(int id, String gradDidactic, UserEntity idUser) {
        this.id = id;
        this.gradDidactic = gradDidactic;
        this.idUser = idUser;
    }
}
