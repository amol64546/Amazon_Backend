package com.bada.bazaar.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

@Getter
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties({"typeMessageCode", "detailMessageCode", "titleMessageCode"})
public class ApiErrorResponse implements ErrorResponse {

  protected HttpStatusCode statusCode;
  protected String errorMessage;
  protected String actionsRequired;

  public ApiErrorResponse(Throwable ex) {
    if (ex instanceof ApplicationException) {
      ApplicationException applicationException = (ApplicationException) ex;
      this.errorMessage = applicationException.getError().getErrorMessage();
      this.actionsRequired = applicationException.getError().getActionRequired();
      this.statusCode = applicationException.getError().getStatusCode();
    }
  }

  public ApiErrorResponse(String errorMessage, String actionsRequired) {
    this.errorMessage = errorMessage;
    this.actionsRequired = actionsRequired;
  }


  @Override
  public HttpStatusCode getStatusCode() {
    return this.statusCode;
  }

  @Override
  public ProblemDetail getBody() {
    return null;
  }
}
