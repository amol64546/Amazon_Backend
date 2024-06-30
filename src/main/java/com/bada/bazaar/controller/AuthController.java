package com.bada.bazaar.controller;

import com.bada.bazaar.requestDto.LoginRequest;
import com.bada.bazaar.requestDto.UserPostRequestDto;
import com.bada.bazaar.responseDto.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("v1/auth")
public interface AuthController {

  @PostMapping("register")
  ResponseEntity<UserResponseDto> register(
    @Validated @Valid @RequestBody UserPostRequestDto userPostRequestDto
  );

  @PostMapping("login")
  ResponseEntity<String> login(LoginRequest loginRequest);

}

