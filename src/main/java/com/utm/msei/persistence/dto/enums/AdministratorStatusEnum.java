package com.utm.msei.persistence.dto.enums;

public enum AdministratorStatusEnum {

    DIRECTOR("Director"),
    DIRECTOR_ADJ("Director Adjunct");

    private final String type;

    AdministratorStatusEnum(String type) {
        this.type = type;
    }

    private String getValue() {
        return type;
    }
}
