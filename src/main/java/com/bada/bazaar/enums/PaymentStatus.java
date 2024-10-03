package com.bada.bazaar.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatus {

  SUCCESS("SUCCESS"),
  FAILED("FAILED");

  private final String value;

  PaymentStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
