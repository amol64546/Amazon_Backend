package com.bada.bazaar.exception;

import static com.bada.bazaar.error.CommonErrors.GET_API_FAILURE;
import static java.lang.String.format;

import org.springframework.web.client.RestClientResponseException;

public class RestGetException extends ApplicationException {

    public RestGetException(String errorMessage) {
        super(GET_API_FAILURE, errorMessage);
    }

    public RestGetException(String errorMessage, RestClientResponseException restException) {
        super(GET_API_FAILURE, errorMessage, restException);
    }

    public RestGetException(RestClientResponseException restException) {
        super(GET_API_FAILURE, "Failed to retrieve data from the URL! ", restException);
        this.setDetailedErrorMessage(format(
            "Error on GET operation : %s - %s",
            restException.getRawStatusCode(), restException.getResponseBodyAsString()));
    }

    public RestGetException(
        String url, String errorMessage, RestClientResponseException restException) {

        super(GET_API_FAILURE, errorMessage, restException);
        this.setDetailedErrorMessage(format(
            "Error on GET %s : %s - %s",
            url, restException.getRawStatusCode(), restException.getResponseBodyAsString()));
    }

    public RestGetException(RestClientResponseException restException, String url) {

        super(GET_API_FAILURE, "Failed to retrieve data from the URL", restException);
        this.setDetailedErrorMessage(format(
            "Error on GET %s : %s - %s",
            url, restException.getRawStatusCode(), restException.getResponseBodyAsString()));
    }
}
