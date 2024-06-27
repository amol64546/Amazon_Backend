package com.bada.bazaar.exception;

import com.bada.bazaar.error.Error;

public class MethodArgumentsNotValidException extends ApiException {

  public MethodArgumentsNotValidException(Error error, String detailedErrorMessage,
      Object errorObject) {
    super(error, detailedErrorMessage, errorObject);
  }
}
