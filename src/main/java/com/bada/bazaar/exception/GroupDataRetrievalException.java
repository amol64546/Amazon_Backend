package com.bada.bazaar.exception;


import com.bada.bazaar.error.CommonErrors;

public class GroupDataRetrievalException extends ApplicationException {

    public GroupDataRetrievalException(String errorMessage) {
        super(CommonErrors.GROUP_DATA_RETRIEVAL_FAILURE, errorMessage);
    }

    public GroupDataRetrievalException(String errorMessage, Throwable throwable) {
        super(CommonErrors.GROUP_DATA_RETRIEVAL_FAILURE, errorMessage, throwable);
    }
}
