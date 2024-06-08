package com.BadaBazaar.BadaBazaar.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CardType {

    VISA("VISA"),
    MASTERCARD("MASTERCARD"),
    RUPAY("RUPAY");

    private final String value;

    CardType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
