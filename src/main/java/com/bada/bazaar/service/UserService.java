package com.bada.bazaar.service;

import com.bada.bazaar.dto.request.UserLoginRequest;
import com.bada.bazaar.dto.request.UserRegisterRequestDto;
import com.bada.bazaar.dto.response.UserResponseDto;

public interface UserService {

  String login(UserLoginRequest userLoginRequest);

  UserResponseDto register(UserRegisterRequestDto userRegisterRequestDto);
}
