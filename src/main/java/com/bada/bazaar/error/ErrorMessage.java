package com.bada.bazaar.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ErrorMessage {

  private String rootOrigin;

  private String rootMessage;

  private String message;

  private String actionRequired;

  private ErrorMessage cause;
  private String origin;
  private HttpStatusCode httpStatusCode;

  @Builder.Default
  private long timestamp = Instant.now().getEpochSecond();

  public ErrorMessage(String message) {
    this.message = message;
  }

  public ErrorMessage(String message, String origin) {
    this.message = message;
    this.origin = origin;
  }

  public ErrorMessage(String message, String origin, HttpStatusCode errorCode, long timestamp) {
    this.message = message;
    this.origin = origin;
    this.httpStatusCode = errorCode;
    this.timestamp = timestamp;
  }

  public ErrorMessage(String subError, String actionRequired2, Object object, String appName,
      HttpStatusCode status, long timestamp2) {
    this.actionRequired = actionRequired2;
    this.httpStatusCode = status;
    this.timestamp = timestamp2;
  }
}
