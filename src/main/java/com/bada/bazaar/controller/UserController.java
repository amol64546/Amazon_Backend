package com.bada.bazaar.controller;

import com.bada.bazaar.requestDto.UserLoginRequest;
import com.bada.bazaar.requestDto.UserPostRequestDto;
import com.bada.bazaar.responseDto.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("v1/users")
public interface UserController {

  @PostMapping("register")
  ResponseEntity<UserResponseDto> register(
    @Validated @Valid @RequestBody UserPostRequestDto userPostRequestDto
  );

  @PostMapping("login")
  ResponseEntity<String> login(UserLoginRequest userLoginRequest);

}

