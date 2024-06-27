package com.bada.bazaar.responseDto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.time.LocalTime.now;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.bada.bazaar.config.App;
import com.bada.bazaar.error.Error;
import com.bada.bazaar.error.ErrorMessage;
import com.bada.bazaar.exception.ApplicationException;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@JsonInclude(NON_EMPTY)
public class ApiErrorResponse extends ErrorResponse {

  protected HttpStatusCode httpStatusCode;

  public ApiErrorResponse() {
    super();
    this.timestamp = System.currentTimeMillis();
    this.httpStatusCode = HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR.value());
    this.subErrors = new ArrayList<>();
    this.origin = App.getAppName();
  }

  public ApiErrorResponse(HttpStatusCode status) {

    this();
    this.httpStatusCode = status;
    this.errorMessage = "Encountered an unexpected error";
  }

  public ApiErrorResponse(String errorMessage) {

    this();
    this.errorMessage = errorMessage;
  }

  public ApiErrorResponse(HttpStatusCode httpStatusCode, String errorMessage) {

    this(httpStatusCode);
    this.errorMessage = errorMessage;
  }

  public ApiErrorResponse(HttpStatusCode status, Throwable ex) {

    this(status);
    if (nonNull(ex)) {
      this.errorMessage = ex.getMessage();
      ofNullable(ex.getCause()).map(Throwable::getMessage).map(ErrorMessage::new)
          .ifPresent(nestedCause -> cause = nestedCause);
      if (ex instanceof ApplicationException) {
        ApplicationException applicationException = (ApplicationException) ex;
        this.origin = applicationException.getOrigin();
        this.errorCode = applicationException.getError().getErrorCode();
        this.errorObject = applicationException.getErrorObject();
        if (((ApplicationException) ex).getNestedErrorMessage() != null) {
          this.cause = ((ApplicationException) ex).getNestedErrorMessage();
        } else {
          ofNullable(applicationException.getMessage()).map(ErrorMessage::new)
              .ifPresent(nestedCause -> this.cause = nestedCause);
        }
        addSubErrors(applicationException.getSubErrors());
        addActionsRequired(applicationException.getActionsRequired());
      }
    }
  }

  public ApiErrorResponse(HttpStatusCode status, String message, Throwable ex) {

    this(status, message);
    ofNullable(ex).map(Throwable::getMessage).map(ErrorMessage::new)
        .ifPresent(nestedCause -> cause = nestedCause);

    if (nonNull(ex)) {
      this.errorMessage = ex.getMessage();
      ofNullable(ex.getCause()).map(Throwable::getMessage).map(ErrorMessage::new)
          .ifPresent(nestedCause -> cause = nestedCause);
      if (ex instanceof ApplicationException) {
        ApplicationException applicationException = (ApplicationException) ex;
        this.origin = applicationException.getOrigin();
        this.errorCode = applicationException.getError().getErrorCode();
        this.detailedErrorMessage = applicationException.getDetailedErrorMessage();
        ofNullable(applicationException.getNestedErrorMessage())
            .ifPresent(nestedCause -> this.cause = nestedCause);
        addSubErrors(applicationException.getSubErrors());
        addActionsRequired(applicationException.getActionsRequired());
      }
    }
  }

  public ApiErrorResponse(HttpStatusCode status, String errorMessage, ErrorMessage subErrorMessage,
      String actionRequired) {

    this(status, errorMessage);
    if (nonNull(subErrorMessage)) {
      this.subErrors.add(subErrorMessage);
    }
    if (isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }
  }

  public ApiErrorResponse(HttpStatusCode status, String errorMessage, ErrorMessage subErrorMessage,
      String actionRequired, int errorCode) {

    this(status, errorMessage);

    this.errorCode = errorCode;
    if (nonNull(subErrorMessage)) {
      this.subErrors.add(subErrorMessage);
    }
    if (isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }
  }

  public ApiErrorResponse(HttpStatusCode status, String errorMessage, ErrorMessage subErrorMessage,
      String actionRequired, boolean isInvalidToken) {

    this(status, errorMessage);
    if (isInvalidToken) {
      this.errorCode = 401000;
    }

    if (nonNull(subErrorMessage)) {
      this.subErrors.add(subErrorMessage);
    }
    if (isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }
  }

  // Generic
  public ApiErrorResponse(Error error) {
    super();
    this.timestamp = System.currentTimeMillis();
    this.httpStatusCode = HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR.value());
    this.subErrors = new ArrayList<>();
    this.origin = App.getAppName();
    this.errorMessage = error.getErrorMessage();
    this.detailedErrorMessage = error.getActionRequired();
  }

  public ApiErrorResponse(ApplicationException applicationException) {
    this(applicationException.getError());
    this.origin = applicationException.getOrigin();
    ofNullable(applicationException.getCause()).map(Throwable::getMessage).map(ErrorMessage::new)
        .ifPresent(nestedCause -> this.cause = nestedCause);
  }

  public ApiErrorResponse(Throwable throwable) {
    this(throwable.getMessage());
    ofNullable(throwable.getCause()).map(Throwable::getMessage).map(ErrorMessage::new)
        .ifPresent(nestedCause -> cause = nestedCause);
  }

  public ApiErrorResponse(HttpStatusCode status, Error error) {
    this(status, error.getErrorMessage());
  }

  public ApiErrorResponse(HttpStatusCode status, String errorMessage, List<ErrorMessage> subErrors,
      List<String> actionsRequired) {

    this(status, errorMessage);
    if (!isEmpty(subErrors)) {
      this.subErrors.addAll(subErrors);
    }
    if (!isEmpty(actionsRequired)) {
      this.actionsRequired.addAll(actionsRequired);
    }
  }

  @Override
  public void addSubError(ErrorMessage subError) {

    if (nonNull(subError)) {
      this.subErrors.add(subError);
    }
  }

  @Override
  public void addSubErrors(List<ErrorMessage> subErrors) {

    if (!isEmpty(subErrors)) {
      this.subErrors.addAll(subErrors);
    }
  }

  @Override
  public void addActionRequired(String actionRequired) {

    if (isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }
  }

  @Override
  public void addActionsRequired(List<String> actionsRequired) {

    if (!isEmpty(actionsRequired)) {
      this.actionsRequired.addAll(actionsRequired);
    }
  }
}
