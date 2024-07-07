package com.bada.bazaar.error;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import jakarta.validation.ConstraintViolationException;
import java.net.ConnectException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RuntimeExceptionHandler {


  @ExceptionHandler(ConnectException.class)
  public final ResponseEntity<String> handlePostgresConnectionException(Exception exception) {
    log.error("Postgres Connection Exception error :: ", exception);
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
      .body("We are facing technical issue, Please try again after sometime.");

  }


  @ExceptionHandler({ApiException.class})
  protected ResponseEntity<ApiErrorResponse> handleApiException(ApiException e) {
    return new ResponseEntity<>(new ApiErrorResponse(e), e.getError().getStatusCode());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<ApiErrorResponse> handleDataIntegrityViolation(
    DataIntegrityViolationException exception) {

    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR.value());

    if (exception.getCause() instanceof ConstraintViolationException) {
      httpStatusCode = HttpStatusCode.valueOf(CONFLICT.value());

      return new ResponseEntity<>(new ApiErrorResponse(
        "Constraint violation occurred. This operation cannot be completed.",
        "Verify that all data meets the required constraints and try again. Review the specific constraints and adjust the input data accordingly."),
        httpStatusCode);
    }

    return new ResponseEntity<>(
      new ApiErrorResponse("Database transaction failed. Please try again later.",
        "Ensure all database connections are stable and retry the operation. If the issue persists, check the transaction logs and database configuration."),
      httpStatusCode);
  }
}

