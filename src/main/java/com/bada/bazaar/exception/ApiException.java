
package com.bada.bazaar.exception;

import lombok.Data;

@Data
public class ApiException extends RuntimeException {

  protected final Error error;

}
