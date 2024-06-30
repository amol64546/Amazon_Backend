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

  public ApiErrorResponse(ApiException ex) {
        this.errorMessage = ex.getError().getErrorMessage();
        this.actionsRequired = ex.getError().getActionRequired();
        this.statusCode = ex.getError().getStatusCode();
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
