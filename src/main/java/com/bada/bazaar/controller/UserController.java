package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.UserLoginRequest;
import com.bada.bazaar.dto.request.UserRegisterRequestDto;
import com.bada.bazaar.dto.response.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/auth")
public interface UserController {

  @PostMapping("/register")
  ResponseEntity<UserResponseDto> register(
    @Validated @Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto
  );

  @PostMapping("/login")
  ResponseEntity<ModelMap> login(
    @Validated @Valid @RequestBody UserLoginRequest userLoginRequest);

}

