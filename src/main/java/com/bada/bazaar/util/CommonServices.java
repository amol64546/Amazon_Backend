package com.bada.bazaar.util;

import com.bada.bazaar.error.ApiException;
import com.bada.bazaar.error.ErrorConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonServices {

  private final ObjectMapper objectMapper;

  public String prettyPrint(Object obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new ApiException(ErrorConstants.JSON_PARSING_EXCEPTION);
    }
  }

}

