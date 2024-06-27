package com.bada.bazaar.exception;


import static com.bada.bazaar.error.CommonErrors.UNSUPPORTED_OPERATION;

public class UnsupportedOperationException extends ApplicationException {

    public UnsupportedOperationException(String errorMessage) {
        super(UNSUPPORTED_OPERATION, errorMessage);
    }

    public UnsupportedOperationException(String errorMessage, Throwable throwable) {
        super(UNSUPPORTED_OPERATION, errorMessage, throwable);
    }
}
