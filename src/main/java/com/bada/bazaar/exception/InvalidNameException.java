package com.bada.bazaar.exception;


import com.bada.bazaar.error.CommonErrors;

public class InvalidNameException extends ValidationException {

    public InvalidNameException(String errorMessage) {
        super(CommonErrors.INVALID_NAME, errorMessage);
    }

    public InvalidNameException(String errorMessage, Throwable throwable) {
        super(CommonErrors.INVALID_NAME, errorMessage, throwable);
    }
}
