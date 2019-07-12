package com.ignite.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of the identification document
 */
public enum IdTypeEnum {
  IDENTITY_CARD("identity card"),
  
  DRIVING_LICENSE("driving license"),
  
  PASSPORT("passport");

  private String value;

  IdTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static IdTypeEnum fromValue(String text) {
    for (IdTypeEnum b : IdTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}