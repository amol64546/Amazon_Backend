package com.bada.bazaar.error;

import java.io.Serializable;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class Error implements Serializable {

  private static final long serialVersionUID = 1L;

  private HttpStatusCode httpStatusCode;

  private int errorCode;

  private String errorMessage;

  private String actionRequired;

  private ErrorMessage errorMessageDescription;

  public Error(HttpStatusCode httpStatusCode, int errorCode, String errorMessage,
      String actionRequired, ErrorMessage errorMessageDescription) {
    super();
    this.httpStatusCode = httpStatusCode;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.actionRequired = actionRequired;
    this.errorMessageDescription = errorMessageDescription;
  }

  public Error(HttpStatusCode httpStatusCode, int errorCode, String errorMessage,
      String actionRequired) {
    super();
    this.httpStatusCode = httpStatusCode;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.actionRequired = actionRequired;
  }
}
