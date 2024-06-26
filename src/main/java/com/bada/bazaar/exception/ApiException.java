
package com.bada.bazaar.exception;

import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ApiException extends RuntimeException {
  protected HttpStatusCode httpStatusCode;

  public ApiException(Error error) {
    super(error);
    this.httpStatusCode = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
    if (Objects.nonNull(error)) {
      this.httpStatusCode = error.getHttpStatusCode();
    }

  }

}
