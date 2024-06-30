package com.bada.bazaar.exception;

import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {
  protected final Error error;
}