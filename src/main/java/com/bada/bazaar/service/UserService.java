package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.UserLoginRequest;
import com.bada.bazaar.requestDto.UserPostRequestDto;
import com.bada.bazaar.responseDto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  String login(UserLoginRequest userLoginRequest);

  UserDetails loadUserByUsername(String username);

  UserResponseDto register(UserPostRequestDto userPostRequestDto);
}
