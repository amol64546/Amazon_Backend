package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.LoginRequest;
import com.bada.bazaar.requestDto.UserPostRequestDto;
import com.bada.bazaar.responseDto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

  String login(LoginRequest loginRequest);

  UserDetails loadUserByUsername(String username);

  UserResponseDto register(UserPostRequestDto userPostRequestDto);
}
