package com.bada.bazaar.exception;

import static com.bada.bazaar.error.CommonErrors.REST_API_FAILURE;
import static java.lang.String.format;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;

public class RestException extends ApplicationException {

    public RestException(String errorMessage) {
        super(REST_API_FAILURE, errorMessage);
    }

    public RestException(String errorMessage, RestClientResponseException restException) {
        super(REST_API_FAILURE, errorMessage, restException);
    }

    public RestException(RestClientResponseException restException) {
        super(REST_API_FAILURE, restException);
        this.setDetailedErrorMessage(format(
            "Error on POST operation : %s - %s",
            restException.getRawStatusCode(), restException.getResponseBodyAsString()));
    }

    public RestException(
        HttpMethod method, String url, Object payload, HttpHeaders headers, String errorMessage,
        RestClientResponseException restException) {

        super(REST_API_FAILURE, errorMessage, restException);
        this.setDetailedErrorMessage(format(
            "Error on %s %s with payload %s and headers %s : %s - %s",
            method, url, payload, headers, restException.getRawStatusCode(),
            restException.getResponseBodyAsString()));
    }

    public RestException(
        HttpMethod method, String url, Object payload, HttpHeaders headers) {

        super(REST_API_FAILURE, "ERROR while performing REST operation! ");
        this.setDetailedErrorMessage(format(
            "Error on %s %s with payload %s and headers %s", method, url, payload, headers));
    }

    public RestException(
        HttpMethod method, String url, Object payload, HttpHeaders headers, RestClientResponseException restException) {

        super(REST_API_FAILURE, "ERROR while performing REST operation! ", restException);
        this.setDetailedErrorMessage(format(
            "Error on %s %s with payload %s and headers %s : %s - %s",
            method, url, payload, headers, restException.getRawStatusCode(),
            restException.getResponseBodyAsString()));
    }
}
