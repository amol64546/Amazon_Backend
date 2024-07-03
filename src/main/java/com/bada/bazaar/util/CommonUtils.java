package com.bada.bazaar.util;

import com.bada.bazaar.enums.Role;
import com.bada.bazaar.exception.ApiException;
import com.bada.bazaar.exception.ErrorConstants;
import com.bada.bazaar.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtils {

  public String prettyPrint(Object obj) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new ApiException(ErrorConstants.JSON_PARSING_EXCEPTION);
    }
  }

  public User getUserInfo(HttpServletRequest request){
    return User.builder()
      .id(Integer.valueOf(request.getHeader("userId")))
      .username(request.getHeader("username"))
      .role(Role.valueOf(request.getHeader("userType")))
      .build();
  }


}

