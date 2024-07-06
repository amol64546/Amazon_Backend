package com.bada.bazaar.error;

import org.springframework.http.HttpStatus;

public class ErrorConstants {

  //400
  public static final Error JSON_PARSING_EXCEPTION =
    new Error(HttpStatus.BAD_REQUEST,
      "", "");

  //404
  public static final Error SELLER_NOT_FOUND =
    new Error(HttpStatus.NOT_FOUND,
      "Seller Not found", "Please verify the SellerId");

  //409
  public static final Error CONFLICT =
    new Error(HttpStatus.CONFLICT,
      "Conflict", "Verify parameters");

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
}

