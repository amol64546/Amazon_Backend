package com.bada.bazaar.exception;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.bada.bazaar.error.Error;
import com.bada.bazaar.error.ErrorMessage;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
public class ApiException extends ApplicationException {

  protected HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR.value());

  public ApiException(Error error) {
    super(error);
    if (nonNull(error)) {
      httpStatusCode = error.getHttpStatusCode();
    }
  }

  public ApiException(Error error, String errorMessage) {
    super(error, errorMessage);
    if (nonNull(error)) {
      httpStatusCode = error.getHttpStatusCode();
    }
  }

  public ApiException(Error error, String detailedErrorMessage, ErrorMessage errorMessage) {
    super(error, detailedErrorMessage, errorMessage);
    if (nonNull(error)) {
      httpStatusCode = error.getHttpStatusCode();
    }
  }

  public ApiException(Error error, ErrorMessage errorMessage) {
    super(error, errorMessage);
    if (nonNull(error)) {
      httpStatusCode = error.getHttpStatusCode();
    }
  }

  public ApiException(Error error, String detailedErrorMessage, Object errorObject) {
    super(error, detailedErrorMessage, errorObject);
    if (nonNull(error)) {
      httpStatusCode = error.getHttpStatusCode();
    }
  }

  public ApiException(Error error, ErrorMessage errorMessage, Object errorObject) {
    super(error, errorMessage, errorObject);
    if (nonNull(error)) {
      httpStatusCode = error.getHttpStatusCode();
    }
  }

  public ApiException(Error error, Throwable throwable) {
    super(error, throwable);
    if (nonNull(error)) {
      httpStatusCode = error.getHttpStatusCode();
    }
  }

  public ApiException(Error error, String errorMessage, Throwable throwable) {
    super(error, errorMessage, throwable);
    if (nonNull(error)) {
      httpStatusCode = error.getHttpStatusCode();
    }
  }
}
