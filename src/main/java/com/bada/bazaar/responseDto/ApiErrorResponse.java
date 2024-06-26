//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.bada.bazaar.responseDto;

import com.aidtaas.mobius.error.services.config.App;
import com.aidtaas.mobius.error.services.error.Error;
import com.aidtaas.mobius.error.services.error.ErrorMessage;
import com.aidtaas.mobius.error.services.exception.ApplicationException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.util.CollectionUtils;

@JsonInclude(Include.NON_EMPTY)
public class ApiErrorResponse extends ErrorResponse {
  protected HttpStatusCode httpStatusCode;

  public ApiErrorResponse() {
    this.timestamp = DateTime.now().getMillis();
    this.httpStatusCode = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
    this.subErrors = new ArrayList();
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
    if (Objects.nonNull(ex)) {
      this.errorMessage = ex.getMessage();
      Optional.ofNullable(ex.getCause()).map(Throwable::getMessage).map(ErrorMessage::new).ifPresent((nestedCause) -> {
        this.cause = nestedCause;
      });
      if (ex instanceof ApplicationException) {
        ApplicationException applicationException = (ApplicationException)ex;
        this.origin = applicationException.getOrigin();
        this.errorCode = applicationException.getError().getErrorCode();
        this.errorObject = applicationException.getErrorObject();
        if (((ApplicationException)ex).getNestedErrorMessage() != null) {
          this.cause = ((ApplicationException)ex).getNestedErrorMessage();
        } else {
          Optional.ofNullable(applicationException.getMessage()).map(ErrorMessage::new).ifPresent((nestedCause) -> {
            this.cause = nestedCause;
          });
        }

        this.addSubErrors(applicationException.getSubErrors());
        this.addActionsRequired(applicationException.getActionsRequired());
      }
    }

  }

  public ApiErrorResponse(HttpStatusCode status, String message, Throwable ex) {
    this(status, message);
    Optional.ofNullable(ex).map(Throwable::getMessage).map(ErrorMessage::new).ifPresent((nestedCause) -> {
      this.cause = nestedCause;
    });
    if (Objects.nonNull(ex)) {
      this.errorMessage = ex.getMessage();
      Optional.ofNullable(ex.getCause()).map(Throwable::getMessage).map(ErrorMessage::new).ifPresent((nestedCause) -> {
        this.cause = nestedCause;
      });
      if (ex instanceof ApplicationException) {
        ApplicationException applicationException = (ApplicationException)ex;
        this.origin = applicationException.getOrigin();
        this.errorCode = applicationException.getError().getErrorCode();
        this.detailedErrorMessage = applicationException.getDetailedErrorMessage();
        Optional.ofNullable(applicationException.getNestedErrorMessage()).ifPresent((nestedCause) -> {
          this.cause = nestedCause;
        });
        this.addSubErrors(applicationException.getSubErrors());
        this.addActionsRequired(applicationException.getActionsRequired());
      }
    }

  }

  public ApiErrorResponse(HttpStatusCode status, String errorMessage, ErrorMessage subErrorMessage, String actionRequired) {
    this(status, errorMessage);
    if (Objects.nonNull(subErrorMessage)) {
      this.subErrors.add(subErrorMessage);
    }

    if (StringUtils.isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }

  }

  public ApiErrorResponse(HttpStatusCode status, String errorMessage, ErrorMessage subErrorMessage, String actionRequired, int errorCode) {
    this(status, errorMessage);
    this.errorCode = errorCode;
    if (Objects.nonNull(subErrorMessage)) {
      this.subErrors.add(subErrorMessage);
    }

    if (StringUtils.isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }

  }

  public ApiErrorResponse(HttpStatusCode status, String errorMessage, ErrorMessage subErrorMessage, String actionRequired, boolean isInvalidToken) {
    this(status, errorMessage);
    if (isInvalidToken) {
      this.errorCode = 401000;
    }

    if (Objects.nonNull(subErrorMessage)) {
      this.subErrors.add(subErrorMessage);
    }

    if (StringUtils.isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }

  }

  public ApiErrorResponse(Error error) {
    this.timestamp = DateTime.now().getMillis();
    this.httpStatusCode = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
    this.subErrors = new ArrayList();
    this.origin = App.getAppName();
    this.errorMessage = error.getErrorMessage();
    this.detailedErrorMessage = error.getActionRequired();
  }

  public ApiErrorResponse(ApplicationException applicationException) {
    this(applicationException.getError());
    this.origin = applicationException.getOrigin();
    Optional.ofNullable(applicationException.getCause()).map(Throwable::getMessage).map(ErrorMessage::new).ifPresent((nestedCause) -> {
      this.cause = nestedCause;
    });
  }

  public ApiErrorResponse(Throwable throwable) {
    this(throwable.getMessage());
    Optional.ofNullable(throwable.getCause()).map(Throwable::getMessage).map(ErrorMessage::new).ifPresent((nestedCause) -> {
      this.cause = nestedCause;
    });
  }

  public ApiErrorResponse(HttpStatusCode status, Error error) {
    this(status, error.getErrorMessage());
  }

  public ApiErrorResponse(HttpStatusCode status, String errorMessage, List<ErrorMessage> subErrors, List<String> actionsRequired) {
    this(status, errorMessage);
    if (!CollectionUtils.isEmpty(subErrors)) {
      this.subErrors.addAll(subErrors);
    }

    if (!CollectionUtils.isEmpty(actionsRequired)) {
      this.actionsRequired.addAll(actionsRequired);
    }

  }

  public void addSubError(ErrorMessage subError) {
    if (Objects.nonNull(subError)) {
      this.subErrors.add(subError);
    }

  }

  public void addSubErrors(List<ErrorMessage> subErrors) {
    if (!CollectionUtils.isEmpty(subErrors)) {
      this.subErrors.addAll(subErrors);
    }

  }

  public void addActionRequired(String actionRequired) {
    if (StringUtils.isNotEmpty(actionRequired)) {
      this.actionsRequired.add(actionRequired);
    }

  }

  public void addActionsRequired(List<String> actionsRequired) {
    if (!CollectionUtils.isEmpty(actionsRequired)) {
      this.actionsRequired.addAll(actionsRequired);
    }

  }

  public HttpStatusCode getHttpStatusCode() {
    return this.httpStatusCode;
  }
}
