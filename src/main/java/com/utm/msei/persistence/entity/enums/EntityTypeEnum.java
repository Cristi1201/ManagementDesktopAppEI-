package com.utm.msei.persistence.entity.enums;

public enum EntityTypeEnum {

    ADMINISTRATION("administration"),
    STUDENT("elev"),
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
