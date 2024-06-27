package com.bada.bazaar.handler;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

import com.bada.bazaar.config.App;
import com.bada.bazaar.error.ErrorMessage;
import com.bada.bazaar.exception.AccessViolationException;
import com.bada.bazaar.exception.ApiException;
import com.bada.bazaar.exception.ApplicationException;
import com.bada.bazaar.exception.TokenException;
import com.bada.bazaar.exception.ValidationException;
import com.bada.bazaar.responseDto.ApiErrorResponse;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Value("${spring.application.name}")
  private String applicationName;

  @PostConstruct
  private void init() {
    App.setAppName(applicationName);
  }


  @ExceptionHandler(ConnectException.class)
  public final ResponseEntity<String> handlePostgresConnectionException(Exception exception) {
    log.error("Postgres Connection Exception error :: ", exception);
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
      .body("We are facing technical issue, Please try again after sometime.");
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
    MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status,
    WebRequest request) {
    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(BAD_REQUEST.value());
    String errorMessage = format("Parameter %s is missing", ex.getParameterName());
    log.error(errorMessage, ex);
    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode, errorMessage), httpStatusCode);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
    HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status,
    WebRequest request) {
    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(UNSUPPORTED_MEDIA_TYPE.value());
    StringBuilder builder = new StringBuilder();
    builder.append(ex.getContentType());
    builder.append("Media type is not supported. Supported media types are ");
    ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
    String errorMessage = builder.substring(0, builder.length() - 2);
    log.error(errorMessage, ex);
    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode, errorMessage), httpStatusCode);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<ApiErrorResponse> handleConstraintViolation(
    ConstraintViolationException ex) {
    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(BAD_REQUEST.value());
    log.error("Validation error :: ", ex);
    ApiErrorResponse apiError = new ApiErrorResponse(httpStatusCode, "Constraint Violation");
    ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
      .map(ErrorMessage::new).forEach(apiError::addSubError);
    return new ResponseEntity<>(apiError, httpStatusCode);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {
    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(BAD_REQUEST.value());
    ServletWebRequest servletWebRequest = (ServletWebRequest) request;
    String errorMessage = format("Malformed JSON request : %s to %s ",
      servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
    log.error(errorMessage, ex);
    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode, errorMessage), httpStatusCode);
  }

  @ExceptionHandler(HttpMessageNotWritableException.class)
  protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {
    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR.value());
    String errorMessage = "Error writing JSON output ";
    log.error(errorMessage, errorMessage);
    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode, errorMessage), httpStatusCode);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {
    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(BAD_REQUEST.value());
    String errorMessage = format("Could not find the %s method for URL %s", ex.getHttpMethod(),
      ex.getRequestURL());
    log.error(errorMessage, ex);
    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode, errorMessage), httpStatusCode);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<ApiErrorResponse> handleDataIntegrityViolation(
    DataIntegrityViolationException exception, WebRequest request) {

    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR.value());

    if (exception.getCause() instanceof ConstraintViolationException) {
      httpStatusCode = HttpStatusCode.valueOf(CONFLICT.value());

      return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode,
        "Constraint violation occurred. This operation cannot be completed.",
        ErrorMessage.builder().rootMessage(exception.getMessage()).build(),
        "Verify that all data meets the required constraints and try again. Review the specific constraints and adjust the input data accordingly.",
        409000), httpStatusCode);
    }

    return new ResponseEntity<>(
      new ApiErrorResponse(httpStatusCode, "Database transaction failed. Please try again later.",
        ErrorMessage.builder().rootMessage(exception.getMessage()).build(),
        "Ensure all database connections are stable and retry the operation. If the issue persists, check the transaction logs and database configuration.",
        500000), httpStatusCode);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatch(
    MethodArgumentTypeMismatchException ex, WebRequest request) {

    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(BAD_REQUEST.value());
    String errorMessage = format(
      "The parameter '%s' of value '%s' could not be converted to type '%s' ", ex.getName(),
      ex.getValue(), ex.getRequiredType());
    log.error(errorMessage, ex);
    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode, errorMessage), httpStatusCode);
  }

  @ExceptionHandler(ApplicationException.class)
  protected ResponseEntity<ApiErrorResponse> handleApplicationException(ApplicationException ex,
    WebRequest request) {

    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(FORBIDDEN.value());
    log.error("ApplicationException :: ", ex);
    return new ResponseEntity<>(new ApiErrorResponse(ex.getError()), httpStatusCode);
  }

  @ExceptionHandler(ApiException.class)
  protected ResponseEntity<ApiErrorResponse> handleApiException(ApiException exception,
    WebRequest request) {

    HttpStatusCode httpStatusCode = exception.getHttpStatusCode();
    log.error("-----ApiException : ", exception);

    if (exception instanceof ApplicationException) {
      ApplicationException ex = (ApplicationException) exception;
      if (!ObjectUtils.isEmpty(ex.getErrorObject())) {
        return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode, exception),
          httpStatusCode);
      }
    }

    return new ResponseEntity<>(
      new ApiErrorResponse(httpStatusCode, exception.getError().getErrorMessage(),
        !ObjectUtils.isEmpty(exception.getError().getErrorMessageDescription())
          ? exception.getError().getErrorMessageDescription()
          : exception.getNestedErrorMessage(), exception.getError().getActionRequired(),
        exception.getError().getErrorCode()), httpStatusCode);
  }

  @ExceptionHandler(ValidationException.class)
  protected ResponseEntity<ApiErrorResponse> handleValidationException(ValidationException ex,
    WebRequest request) {

    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(CONFLICT.value());
    log.error("ValidationException :: ", ex);
    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode, ex), httpStatusCode);
  }

  @ExceptionHandler(AccessViolationException.class)
  protected ResponseEntity<ApiErrorResponse> handleAccessViolation(AccessViolationException ex,
    WebRequest request) {

    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(FORBIDDEN.value());
    log.error("ValidationException :: ", ex);
    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode, ex), httpStatusCode);
  }

  @ExceptionHandler(RestClientResponseException.class)
  protected ResponseEntity<ApiErrorResponse> handleApiResponseException(
    RestClientResponseException ex, WebRequest request) {

    HttpStatusCode statusCode = HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR.value());
    log.error("RestClientResponseException :: {} - {}", ex, ex.getResponseBodyAsString());
    return new ResponseEntity<>(new ApiErrorResponse(statusCode, ex.getResponseBodyAsString()),
      statusCode);
  }

  @ExceptionHandler(ExecutionException.class)
  public final ResponseEntity<ApiErrorResponse> handleAllExecutionException(ExecutionException ex,
    WebRequest request) {
    String message = ex.getMessage();
    int len = message.length();
    String substring = message.substring(23, len - 1);
    substring = substring.replace(" ", "");
    log.info("status code is: {}", substring);
    HttpStatusCode statusCode = HttpStatusCode.valueOf(Integer.valueOf(substring));
    log.error("Unexpected error :: ", ex);
    return new ResponseEntity<>(new ApiErrorResponse(statusCode, ex), statusCode);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception exception,
    WebRequest request) {
    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR.value());
    log.error("-----Unexpected error :: ", exception);
    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode, exception), httpStatusCode);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {
    log.info("-----RestExceptionHandler Class, handleMethodArgumentNotValid method-----");
    BindingResult result = ex.getBindingResult();
    Map<String, String> fieldErrors = result.getFieldErrors().stream()
      .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

    return handleExceptionInternal(ex, "Method argument not valid, fieldErrors: " + fieldErrors,
      new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(JpaSystemException.class)
  public final ResponseEntity<ApiErrorResponse> handleTiDBConnectionException(
    JpaSystemException exception, WebRequest request) {
    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value());
    log.error("-----TiDB Connection Exception error :: ", exception);

    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode,
      "We are currently experiencing technical difficulties. Please try again later.",
      ErrorMessage.builder().rootMessage(exception.getMessage()).build(),
      "Ensure that the TiDB service is up and running.", 503001), httpStatusCode);
  }

  @ExceptionHandler(DataAccessResourceFailureException.class)
  public final ResponseEntity<ApiErrorResponse> handleMongoConnectionException(
    DataAccessResourceFailureException exception, WebRequest request) {
    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value());
    log.error("-----Mongo Connection Exception error :: ", exception);

    return new ResponseEntity<>(new ApiErrorResponse(httpStatusCode,
      "We are currently experiencing technical difficulties. Please try again later.",
      ErrorMessage.builder().rootMessage(exception.getMessage()).build(),
      "Ensure that the MongoDB service is up and running.", 503000), httpStatusCode);
  }

  @ExceptionHandler(TokenException.class)
  public final ResponseEntity<ApiErrorResponse> handleThirdPartyException(
    TokenException exception, WebRequest request) {
    HttpStatusCode httpStatusCode = exception.getHttpStatusCode();
    log.error("-----ThirdParty Exception error :: ", exception);

    return new ResponseEntity<>(
      new ApiErrorResponse(httpStatusCode, exception.getError().getErrorMessage(),
        !ObjectUtils.isEmpty(exception.getError().getErrorMessageDescription())
          ? exception.getError().getErrorMessageDescription()
          : exception.getNestedErrorMessage(), exception.getError().getActionRequired(),
        exception.getError().getErrorCode()), httpStatusCode);
  }
}
