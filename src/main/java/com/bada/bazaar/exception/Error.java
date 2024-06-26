package com.bada.bazaar.exception;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class Error implements Serializable {
  private HttpStatusCode httpStatusCode;
  private String errorMessage;
  private String actionRequired;
}
