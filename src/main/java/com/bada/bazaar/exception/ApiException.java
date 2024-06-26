
package com.bada.bazaar.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class ApiException extends RuntimeException {
  protected final HttpStatusCode httpStatusCode;
  protected final Error error;
}
