package com.utm.msei.persistence.dto.enums;

public enum EntityTypeEnum {

    ADMINISTRATION("administration"),
    STUDENT("student"),
    PROFESSOR("professor"),
    PARENT("parent");

    private final String type;

    EntityTypeEnum(String type) {
        this.type = type;
    }

    private String getValue() {
        return type;
    }
}
