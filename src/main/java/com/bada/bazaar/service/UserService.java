package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.UserLoginRequest;
import com.bada.bazaar.requestDto.UserRegisterRequestDto;
import com.bada.bazaar.responseDto.UserResponseDto;

public interface UserService {

  String login(UserLoginRequest userLoginRequest);

  UserResponseDto register(UserRegisterRequestDto userRegisterRequestDto);
}
