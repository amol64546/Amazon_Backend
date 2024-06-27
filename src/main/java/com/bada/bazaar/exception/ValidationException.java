package com.bada.bazaar.exception;

import com.bada.bazaar.error.Error;

public class ValidationException extends ApiException {

    public ValidationException(Error error) {
        super(error);
    }

    public ValidationException(Error error, String errorMessage) {
        super(error, errorMessage);
    }

    public ValidationException(Error error, String detailedErrorMessage, Object errorObject) {
        super(error, detailedErrorMessage, errorObject);
    }

    public ValidationException(Error error, Throwable throwable) {
        super(error, throwable);
    }

    public ValidationException(Error error, String errorMessage, Throwable throwable) {
        super(error, errorMessage, throwable);
    }

}
