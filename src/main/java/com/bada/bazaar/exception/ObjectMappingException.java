package com.bada.bazaar.exception;

import static com.bada.bazaar.error.CommonErrors.OBJECT_MAPPING_FAILURE;

public class ObjectMappingException extends ApplicationException {

    public ObjectMappingException(String errorMessage) {
        super(OBJECT_MAPPING_FAILURE, errorMessage);
    }

    public ObjectMappingException(String errorMessage, Throwable throwable) {
        super(OBJECT_MAPPING_FAILURE, errorMessage, throwable);
    }
}
