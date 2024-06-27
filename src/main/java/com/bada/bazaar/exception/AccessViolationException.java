package com.bada.bazaar.exception;

import com.bada.bazaar.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class AccessViolationException extends ValidationException {

  private static final long serialVersionUID = 1L;

  public AccessViolationException(Error error) {
    super(error);
    httpStatusCode = HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value());
  }

  public AccessViolationException(Error error, String errorMessage) {
    super(error, errorMessage);
    httpStatusCode = HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value());
  }

  public AccessViolationException(Error error, String errorMessage, Object errorObject) {
    super(error, errorMessage, errorObject);
    httpStatusCode = HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value());
  }

  public AccessViolationException(Error error, String errorMessage, Throwable throwable) {
    super(error, errorMessage, throwable);
    httpStatusCode = HttpStatusCode.valueOf(HttpStatus.FORBIDDEN.value());
  }
}
