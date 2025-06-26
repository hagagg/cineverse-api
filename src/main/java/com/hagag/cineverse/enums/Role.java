package com.hagag.cineverse.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role{
    USER,
    PREMIUM,
    ADMIN;

    @JsonCreator
    public static Role fromString(String value) {
        return value == null ? null : Role.valueOf(value.toUpperCase());
    }

}
