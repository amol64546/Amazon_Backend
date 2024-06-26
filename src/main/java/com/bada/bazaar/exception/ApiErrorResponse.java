package com.bada.bazaar.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ApiErrorResponse implements ErrorResponse {
  protected HttpStatusCode httpStatusCode;

  @Override
  public HttpStatusCode getStatusCode() {
    return this.httpStatusCode;
  }

  @Override
  public ProblemDetail getBody() {
    return null;
  }
}
