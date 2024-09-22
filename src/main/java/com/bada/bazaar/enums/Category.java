package com.bada.bazaar.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {

  SPORTS("SPORTS"),
  FASHION("FASHION"),
  ELECTRONICS("ELECTRONICS");

  private final String value;

  Category(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

}
