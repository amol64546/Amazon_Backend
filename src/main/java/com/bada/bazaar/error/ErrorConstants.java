package com.bada.bazaar.error;

import org.springframework.http.HttpStatus;

public class ErrorConstants {

  public static final Error JSON_PARSING_EXCEPTION =
    new Error(HttpStatus.BAD_REQUEST,
      "JSON Parsing Exception", "Please verify the JSON");

  public static final Error USER_ALREADY_EXISTS = new Error(
    HttpStatus.CONFLICT,
    "User Already Exists",
    "Please verify the username"
  );
  public static final Error USER_NOT_FOUND = new Error(
    HttpStatus.NOT_FOUND,
    "User Not Found",
    "Please verify the username"
  );
  public static final Error PRODUCT_NOT_FOUND = new Error(
    HttpStatus.NOT_FOUND,
    "Product Not Found",
    "Please verify the productId"
  );
}

