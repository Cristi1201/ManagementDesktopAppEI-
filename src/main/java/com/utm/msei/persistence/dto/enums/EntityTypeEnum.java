package com.utm.msei.persistence.dto.enums;

import java.util.List;

public enum EntityTypeEnum {

    DIRECTOR("Director"),
    ADJUNCT("Dir. Adjunct"),
    PROFESOR("Profesor"),
    ELEV("Elev"),
    PARINTE("Parinte");

    private final String type;

    EntityTypeEnum(String type) {
        this.type = type;
    }

    private String getValue() {
        return type;
    }
}
