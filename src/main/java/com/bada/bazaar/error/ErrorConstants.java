package com.bada.bazaar.error;

import org.springframework.http.HttpStatus;

public class ErrorConstants {

  public static final Error JSON_PARSING_EXCEPTION =
    new Error(HttpStatus.BAD_REQUEST,
      "JSON Parsing Exception", "Please verify the JSON");

  public static final Error OUT_OF_STOCK = new Error(
    HttpStatus.BAD_REQUEST,
    "Product is out of stock",
    "Wait for stock refilled."
  );

  public static final Error INSUFFICIENT_QTY = new Error(
    HttpStatus.BAD_REQUEST,
    "Product quantity is less",
    "Please decrease quantity"
  );


  public static final Error USER_ALREADY_EXISTS = new Error(
    HttpStatus.CONFLICT,
    "User Already Exists",
    "Please verify the username"
  );
  public static final Error USER_NOT_FOUND = new Error(
    HttpStatus.NOT_FOUND,
    "User Not Found",
    "Please verify the username"
  );
  public static final Error PRODUCT_NOT_FOUND = new Error(
    HttpStatus.NOT_FOUND,
    "Product Not Found",
    "Please verify the productId"
  );

  public static final Error CARD_NOT_FOUND = new Error(
    HttpStatus.NOT_FOUND,
    "Card Not Found",
    "Please verify the cardId"
  );

  public static final Error CUSTOMER_NOT_FOUND = new Error(
    HttpStatus.NOT_FOUND,
    "Customer Not Found",
    "Please verify the customerId"
  );

  public static final Error SELLER_NOT_FOUND = new Error(
    HttpStatus.NOT_FOUND,
    "Seller Not Found",
    "Please verify the sellerId"
  );
}

