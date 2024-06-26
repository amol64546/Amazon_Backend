//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.bada.bazaar.exception;

import java.io.Serializable;
import org.springframework.http.HttpStatusCode;

public class Error implements Serializable {
  private static final long serialVersionUID = 1L;
  private HttpStatusCode httpStatusCode;
  private int errorCode;
  private String errorMessage;
  private String actionRequired;
  private ErrorMessage errorMessageDescription;

  public Error(HttpStatusCode httpStatusCode, int errorCode, String errorMessage, String actionRequired, ErrorMessage errorMessageDescription) {
    this.httpStatusCode = httpStatusCode;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.actionRequired = actionRequired;
    this.errorMessageDescription = errorMessageDescription;
  }

  public Error(HttpStatusCode httpStatusCode, int errorCode, String errorMessage, String actionRequired) {
    this.httpStatusCode = httpStatusCode;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.actionRequired = actionRequired;
  }

  public HttpStatusCode getHttpStatusCode() {
    return this.httpStatusCode;
  }

  public int getErrorCode() {
    return this.errorCode;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }

  public String getActionRequired() {
    return this.actionRequired;
  }

  public ErrorMessage getErrorMessageDescription() {
    return this.errorMessageDescription;
  }
}
