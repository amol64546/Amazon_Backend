package com.bada.bazaar.util;

import com.bada.bazaar.error.ApiException;
import com.bada.bazaar.error.ErrorConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.bada.bazaar.constant.CommonConstant.ROLE;
import static com.bada.bazaar.constant.CommonConstant.USER_ID;

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

  public record UserInfo(String userId, String role) {}

  public static UserInfo getUserInfo(HttpServletRequest request) {
    String role = request.getHeader(ROLE);
    String userId = request.getHeader(USER_ID);
    return new UserInfo(userId, role);
  }


}

