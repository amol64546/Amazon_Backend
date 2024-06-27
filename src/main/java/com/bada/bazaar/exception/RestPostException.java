package com.bada.bazaar.exception;

import static com.bada.bazaar.error.CommonErrors.POST_API_FAILURE;
import static java.lang.String.format;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;

public class RestPostException extends ApplicationException {

  private static final long serialVersionUID = 1L;

  public RestPostException(String errorMessage) {
    super(POST_API_FAILURE, errorMessage);
  }

  public RestPostException(String errorMessage, RestClientResponseException restException) {
    super(POST_API_FAILURE, errorMessage, restException);
  }

  public RestPostException(RestClientResponseException restException) {
    super(POST_API_FAILURE, restException);
    this.setDetailedErrorMessage(format("Error on POST operation : %s - %s",
        restException.getRawStatusCode(), restException.getResponseBodyAsString()));
  }

  public RestPostException(String url, Object payload, HttpHeaders headers, String errorMessage,
      RestClientResponseException restException) {

    super(POST_API_FAILURE, errorMessage, restException);
    this.setDetailedErrorMessage(
        format("Error on POST %s with payload %s and headers %s : %s - %s", url, payload, headers,
            restException.getRawStatusCode(), restException.getResponseBodyAsString()));
  }

  public RestPostException(String url, Object payload, HttpHeaders headers) {

    super(POST_API_FAILURE, "ERROR while performing POST operation! ");
    this.setDetailedErrorMessage(
        format("Error on POST %s with payload %s and headers %s", url, payload, headers));
  }

  public RestPostException(String url, Object payload, HttpHeaders headers,
      RestClientResponseException restException) {

    super(POST_API_FAILURE, "ERROR while performing POST operation! ", restException);
    this.setDetailedErrorMessage(
        format("Error on POST %s with payload %s and headers %s : %s - %s", url, payload, headers,
            restException.getRawStatusCode(), restException.getResponseBodyAsString()));
  }
}
