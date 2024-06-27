package com.bada.bazaar.exception;


import com.bada.bazaar.error.CommonErrors;

public class InvalidTenantException extends ValidationException {

    public InvalidTenantException() {
        super(CommonErrors.INVALID_TENANT_ID);
    }

    public InvalidTenantException(String errorMessage) {
        super(CommonErrors.INVALID_TENANT_ID, errorMessage);
    }
}
