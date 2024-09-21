package com.bada.bazaar.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductStatus {
    AVAILABLE("AVAILABLE"),
    OUT_OF_STOCK("OUT_OF_STOCK");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
