package com.bada.bazaar.exception;

public class QueriesInitNotDoneYetException extends RuntimeException {

  private String status = "error";
  private int httpStatusCode;
  private String msg;

  public QueriesInitNotDoneYetException(int httpStatusCode, String status, String msg) {
    this(httpStatusCode, status, msg, null);
  }

  public QueriesInitNotDoneYetException(int httpStatusCode, String status, String msg,
      Throwable cause) {
    super(msg, cause);
    this.httpStatusCode = httpStatusCode;
    if (status != null)
      this.status = status;
    if (msg != null)
      this.msg = msg;
  }

}
