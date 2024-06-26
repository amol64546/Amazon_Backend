package com.bada.bazaar.handler;

import com.bada.bazaar.exception.ApiException;
import com.bada.bazaar.exception.SellerNotFoundException;
import com.bada.bazaar.responseDto.ApiErrorResponse;
import java.net.ConnectException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RuntimeExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(ConnectException.class)
  public final ResponseEntity<String> handlePostgresConnectionException(Exception exception,
    WebRequest request) {
    log.error("Postgres Connection Exception error :: ", exception);

    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
      .body("We are facing technical issue, Please try again after sometime.");

  }

  @ExceptionHandler(SellerNotFoundException.class)
  public final ResponseEntity<String> handleSellerNotFoundException(Exception exception,
    WebRequest request) {
    log.error(exception.getMessage(), exception);

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(exception.getMessage());

  }

  @ExceptionHandler({ApiException.class})
  protected ResponseEntity<ApiErrorResponse> handleApiException(ApiException exception, WebRequest request) {
    HttpStatusCode httpStatusCode = exception.getHttpStatusCode();
    log.error("-----ApiException : ", exception);
    if (exception instanceof ApplicationException) {
      ApplicationException ex = exception;
      if (!ObjectUtils.isEmpty(ex.getErrorObject())) {
        return new ResponseEntity(new ApiErrorResponse(httpStatusCode, exception), httpStatusCode);
      }
    }

    return new ResponseEntity(new ApiErrorResponse(httpStatusCode, exception.getError().getErrorMessage(), !ObjectUtils.isEmpty(exception.getError().getErrorMessageDescription()) ? exception.getError().getErrorMessageDescription() : exception.getNestedErrorMessage(), exception.getError().getActionRequired(), exception.getError().getErrorCode()), httpStatusCode);
  }

}

