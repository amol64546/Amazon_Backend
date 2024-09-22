
package com.bada.bazaar.error;

public class ApiException extends ApplicationException {

  public ApiException(Error error) {
    super(error);
  }

  public ApiException(Error error, String message) {
    super(error);
  }
}