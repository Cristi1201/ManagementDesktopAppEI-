package com.utm.msei.persistence.dto;

import com.utm.msei.persistence.entity.AdministratieEntity;
import com.utm.msei.persistence.dto.enums.AdministratorStatusEnum;
import com.utm.msei.persistence.mapper.*;
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
    private AdministratorStatusEnum status;
    private UserDto idUser;

    private UserMapper userMapper;


    public AdministratieDto() {}

    public AdministratieDto(AdministratorStatusEnum status, UserDto idUser) {
        this.status = status;
        this.idUser = idUser;
    }

    public AdministratieDto(int id, AdministratorStatusEnum status, UserDto idUser) {
        this.id = id;
        this.status = status;
        this.idUser = idUser;
    }
}
