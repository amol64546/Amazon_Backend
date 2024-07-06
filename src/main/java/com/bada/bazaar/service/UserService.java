package com.bada.bazaar.service;

import com.bada.bazaar.dto.request.UserLoginRequest;
import com.bada.bazaar.dto.request.UserRegisterRequestDto;
import com.bada.bazaar.dto.response.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

  String login(UserLoginRequest userLoginRequest);

  UserResponseDto register(UserRegisterRequestDto userRegisterRequestDto);

  void refreshToken(HttpServletRequest request, HttpServletResponse response);
}
