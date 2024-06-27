package com.bada.bazaar.constant;

import com.bada.bazaar.error.Error;
import org.springframework.http.HttpStatus;

public class ErrorConstants {

  //404
  public static final Error NOT_FOUND =
    new Error(HttpStatus.NOT_FOUND,404001,
      "Not found", "Please verify the id");

}

