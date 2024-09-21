package com.bada.bazaar.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    SELLER("SELLER"),
    CUSTOMER("CUSTOMER");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
