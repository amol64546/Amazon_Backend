package com.bada.bazaar.constant;

import com.bada.bazaar.error.Error;
import org.springframework.http.HttpStatus;

public class ErrorConstants {

  //400
  public static final Error BAD_REQUEST =
    new Error(HttpStatus.BAD_REQUEST,400000,
      "Bad Request", "");

  //404
  public static final Error NOT_FOUND =
    new Error(HttpStatus.NOT_FOUND,404000,
      "Not found", "Please verify the id");

  //409
  public static final Error CONFLICT =
    new Error(HttpStatus.CONFLICT,409000,
      "Conflict", "");

}

