package com.bada.bazaar.error;

import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {
  protected final Error error;
}