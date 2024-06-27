package com.bada.bazaar.exception;

import com.bada.bazaar.error.Error;
import com.bada.bazaar.error.ErrorMessage;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TokenException extends ApiException {

  private static final long serialVersionUID = 1L;

  public TokenException(Error error) {
    super(error);
  }

  public TokenException(Error error, String errorMessage) {
    super(error, errorMessage);
  }

  public TokenException(Error error, ErrorMessage errorMessage) {
    super(error, errorMessage);
  }

  public TokenException(Error error, String errorMessage, Object errorObject) {
    super(error, errorMessage, errorObject);
  }

  public TokenException(Error error, String errorMessage, Throwable throwable) {
    super(error, errorMessage, throwable);
  }
}