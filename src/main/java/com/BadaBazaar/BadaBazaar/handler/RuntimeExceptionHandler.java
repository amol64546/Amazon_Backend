package com.BadaBazaar.BadaBazaar.handler;

import com.BadaBazaar.BadaBazaar.exception.SellerNotFoundException;
import java.net.ConnectException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RuntimeExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(ConnectException.class)
  public final ResponseEntity<String> handleMongoConnectionException(Exception exception,
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

}

