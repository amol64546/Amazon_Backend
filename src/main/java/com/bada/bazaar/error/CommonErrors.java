package com.bada.bazaar.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class CommonErrors {

  protected CommonErrors() {
    throw new IllegalStateException("Utility class");
  }

  // 403 Forbidden
  public static final Error INVALID_TENANT_ID = new Error(FORBIDDEN, 403002,
      "You're not registered as a tenant yet ", "Kindly get yourself registered first ");

  // 500 Internal Server Error
  public static final Error UNEXPECTED_ERROR = new Error(INTERNAL_SERVER_ERROR, 500000,
      "Encountered an unexpected error ", CommonErrorMessages.TRY_AGAIN);

  public static final Error SERVICE_UNAVAILABLE = new Error(INTERNAL_SERVER_ERROR, 500001,
      "On or more service(s) are not up and running ", CommonErrorMessages.TRY_AGAIN);

  public static final Error OBJECT_MAPPING_FAILURE = new Error(INTERNAL_SERVER_ERROR, 500002,
      "Failed convert json to object/map or vice versa ", CommonErrorMessages.VERIFY_REQUEST);

  public static final Error GROUP_DATA_RETRIEVAL_FAILURE = new Error(INTERNAL_SERVER_ERROR, 500003,
      "Failed to get group data from data lake ", "Kindly verify if the group contains data ");

  public static final Error GET_API_FAILURE = new Error(INTERNAL_SERVER_ERROR, 500004,
      "Failed to GET data from the URL ", "Kindly verify if the URL is working ");

  public static final Error POST_API_FAILURE =
      new Error(INTERNAL_SERVER_ERROR, 500005, "Failed to make POST call to an api ",
          "Kindly verify if the POST API service is up and running ");

  public static final Error REST_API_FAILURE =
      new Error(INTERNAL_SERVER_ERROR, 500006, "Failed to make REST call to an api ",
          "Kindly verify if the POST API service is up and running ");

  // 401 UNAUTHORIZED
  public static final Error TENANT_NOT_AUTHORIZED =
      new Error(UNAUTHORIZED, 401000, "You're not authorized to access the resource",
          "Kindly request appropriate access required for your needs to its owner");

  // 400 Bad Request
  public static final Error KAFKA_ERROR = new Error(BAD_REQUEST, 400000,
      "An error occurred in kafka ", "Kindly contact the support team ");

  public static final Error KAFKA_CONSUMPTION_ERROR =
      new Error(BAD_REQUEST, 400001, "An error occurred while consuming a message from kafka ",
          "Kindly contact the support team ");

  public static final Error INVALID_NAME = new Error(BAD_REQUEST, 400002,
      "The name provided is invalid ", "Kindly try with another name ");

  public static final Error UNSUPPORTED_OPERATION = new Error(BAD_REQUEST, 400003,
      "This operation is not yet supported ", "Kindly try another operation ");
}
