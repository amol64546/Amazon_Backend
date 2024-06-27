package com.bada.bazaar.responseDto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.bada.bazaar.error.Error;
import com.bada.bazaar.error.ErrorMessage;
import com.bada.bazaar.exception.ApplicationException;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@JsonInclude(NON_EMPTY)
public class ErrorResponse {

  protected long timestamp;

  protected String origin;

  protected int errorCode;

  protected String errorMessage;

  protected String detailedErrorMessage;

  protected List<ErrorMessage> subErrors;

  protected List<String> actionsRequired;

  protected ErrorMessage cause;

  protected Object errorObject;

  public ErrorResponse() {

    this.timestamp = System.currentTimeMillis();
    this.errorCode = 5001;
    this.errorMessage = "";
    this.subErrors = new ArrayList<>();
    this.actionsRequired = new ArrayList<>();
  }

  public ErrorResponse(String errorMessage) {

    this();
    this.errorMessage = errorMessage;
  }

  public ErrorResponse(Error error) {

    this(error.getErrorMessage());
    this.errorCode = error.getErrorCode();
    this.actionsRequired.add(error.getActionRequired());
  }

  public ErrorResponse(Error error, Throwable ex) {

    this(error);
    this.subErrors = new ArrayList<>();
    this.cause = new ErrorMessage(ex.getMessage());
  }

  public ErrorResponse(Error error, String detailedErrorMessage) {

    this(error);
    if (isNotEmpty(detailedErrorMessage)) {
      this.detailedErrorMessage = detailedErrorMessage;
    }
  }

  public ErrorResponse(Error error, String message, Throwable ex) {

    this(error, message);
    ofNullable(ex).map(Throwable::getMessage).map(ErrorMessage::new)
        .ifPresent(nestedCause -> cause = nestedCause);
  }

  public ErrorResponse(Error error, String detailedErrorMessage, ErrorMessage subError,
      String actionRequired) {

    this(error, detailedErrorMessage);
    if (nonNull(subError)) {
      this.subErrors.add(subError);
    }
    if (isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }
  }

  public ErrorResponse(ApplicationException applicationException) {

    this(applicationException.getError(), applicationException.getDetailedErrorMessage());
    addSubErrors(applicationException.getSubErrors());
    ofNullable(applicationException.getCause()).map(Throwable::getMessage).map(ErrorMessage::new)
        .ifPresent(nestedCause -> this.cause = nestedCause);
  }

  public ErrorResponse(Throwable throwable) {
    this(throwable.getMessage());
    ofNullable(throwable.getCause()).map(Throwable::getMessage).map(ErrorMessage::new)
        .ifPresent(nestedCause -> cause = nestedCause);
  }

  public ErrorResponse(Error error, String detailedErrorMessage, List<ErrorMessage> subErrors,
      List<String> actionsRequired) {

    this(error, detailedErrorMessage);
    if (!isEmpty(subErrors)) {
      this.subErrors.addAll(subErrors);
    }
    if (!isEmpty(actionsRequired)) {
      this.actionsRequired.addAll(actionsRequired);
    }
  }

  public void addSubError(ErrorMessage subError) {

    if (nonNull(subError)) {
      this.subErrors.add(subError);
    }
  }

  public void addSubErrors(List<ErrorMessage> subErrors) {

    if (!isEmpty(subErrors)) {
      this.subErrors.addAll(subErrors);
    }
  }

  public void addActionRequired(String actionRequired) {

    if (isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }
  }

  public void addActionsRequired(List<String> actionsRequired) {

    if (!isEmpty(actionsRequired)) {
      this.actionsRequired.addAll(actionsRequired);
    }
  }
}
