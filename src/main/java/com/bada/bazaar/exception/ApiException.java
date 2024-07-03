
package com.bada.bazaar.exception;

public class ApiException extends ApplicationException {

  public ApiException(Error error) {
    super(error);
  }
}