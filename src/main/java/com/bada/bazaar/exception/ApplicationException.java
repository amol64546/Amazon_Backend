package com.bada.bazaar.exception;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.bada.bazaar.config.App;
import com.bada.bazaar.error.CommonErrors;
import com.bada.bazaar.error.Error;
import com.bada.bazaar.error.ErrorMessage;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApplicationException extends RuntimeException {

  protected final Error error;

  protected String detailedErrorMessage;

  protected Object errorObject;

  protected ErrorMessage nestedErrorMessage;

  private final List<ErrorMessage> subErrors = new ArrayList<>();

  private final List<String> actionsRequired = new ArrayList<>();

  private String origin;

  public ApplicationException() {
    super("Encountered an unexpected error! ");
    this.error = CommonErrors.UNEXPECTED_ERROR;
    this.nestedErrorMessage = null;
    this.origin = App.getAppName();
  }

  public ApplicationException(Error error) {
    super(error.getErrorMessage());
    this.error = error;
    this.nestedErrorMessage = null;
    this.origin = App.getAppName();
  }

  public ApplicationException(Error error, String detailedErrorMessage) {
    super(detailedErrorMessage);
    this.error = error;
    ErrorMessage errorMessage = new ErrorMessage();
    errorMessage.setRootOrigin(App.getAppName());
    errorMessage.setRootMessage(detailedErrorMessage);
    this.nestedErrorMessage = errorMessage;
    this.origin = App.getAppName();
    this.detailedErrorMessage = detailedErrorMessage;
  }

  public ApplicationException(Error error, ErrorMessage errorMessage, Object errorObject) {
    super(error.getErrorMessage());
    this.error = error;
    this.nestedErrorMessage = errorMessage;
    this.origin = App.getAppName();
    this.errorObject = errorObject;
  }

  public ApplicationException(Error error, String detailedErrorMessage, ErrorMessage errorMessage) {
    super(detailedErrorMessage);
    this.error = error;
    this.nestedErrorMessage = errorMessage;
    this.origin = App.getAppName();
    this.detailedErrorMessage = detailedErrorMessage;

  }

  public ApplicationException(Error error, ErrorMessage errorMessage) {
    super(error.getErrorMessage());
    this.error = error;
    this.nestedErrorMessage = errorMessage;
    this.origin = App.getAppName();
    this.detailedErrorMessage = error.getErrorMessage();

  }

  public ApplicationException(Error error, String detailedErrorMessage, Object errorObject) {
    super(detailedErrorMessage);
    this.error = error;
    this.nestedErrorMessage = null;
    this.origin = App.getAppName();
    this.errorObject = errorObject;
    this.detailedErrorMessage = detailedErrorMessage;
  }

  public ApplicationException(Error error, Throwable throwable) {
    super(error.getErrorMessage(), throwable);
    this.nestedErrorMessage = new ErrorMessage(throwable.getMessage(), App.getAppName(),
        error.getHttpStatusCode(), Instant.now().getEpochSecond());
    this.error = error;
    this.origin = App.getAppName();
  }

  public ApplicationException(Error error, String detailedErrorMessage, Throwable throwable) {
    super(detailedErrorMessage, throwable);
    this.nestedErrorMessage = new ErrorMessage(throwable.getMessage(), App.getAppName(),
        HttpStatus.INTERNAL_SERVER_ERROR, Instant.now().getEpochSecond());
    this.error = error;
    this.origin = App.getAppName();
    this.detailedErrorMessage = detailedErrorMessage;
  }

  public ApplicationException(Error error, List<ErrorMessage> subErrors,
      List<String> actionsRequired) {
    this(error);
    if (!isEmpty(subErrors)) {
      this.subErrors.addAll(subErrors);
    }
    if (!isEmpty(actionsRequired)) {
      this.subErrors.addAll(subErrors);
    }
  }

  public ApplicationException(Error error, List<ErrorMessage> subErrors,
      List<String> actionsRequired, Throwable throwable) {

    this(error, throwable);
    if (!isEmpty(subErrors)) {
      this.subErrors.addAll(subErrors);
    }
    if (!isEmpty(actionsRequired)) {
      this.subErrors.addAll(subErrors);
    }
  }

  public ApplicationException addActionRequired(String actionRequired) {

    if (isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }
    return this;
  }

  public ApplicationException addActionsRequired(List<String> actionsRequired) {

    if (!isEmpty(actionsRequired)) {
      this.actionsRequired.addAll(actionsRequired);
    }
    return this;
  }

  public ApplicationException addSubError(String subError) {

    if (isNotEmpty(subError)) {
      this.subErrors.add(new ErrorMessage(subError));
    }
    return this;
  }

  public ApplicationException addSubError(String subError, String actionRequired, HttpStatus status,
      long timestamp) {

    if (isNotEmpty(subError)) {
      this.subErrors.add(new ErrorMessage(subError, actionRequired, null, App.getAppName(), null,
          null, status, timestamp));
    }
    return this;
  }

  public ApplicationException addNestedErrorMessage(String nestedErrorMessage) {

    if (isNotEmpty(nestedErrorMessage)) {
      ErrorMessage rootCause = this.nestedErrorMessage;
      ErrorMessage currentError = new ErrorMessage(nestedErrorMessage);
      currentError.setCause(rootCause);
      this.nestedErrorMessage = currentError;
    }
    return this;
  }
}
