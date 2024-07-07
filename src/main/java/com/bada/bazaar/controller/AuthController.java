package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.UserLoginRequest;
import com.bada.bazaar.dto.request.UserRegisterRequestDto;
import com.bada.bazaar.dto.response.UserResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authentication")
@RequestMapping("/v1/auth")
public interface AuthController {

  @PostMapping("/register")
  ResponseEntity<UserResponseDto> register(
    @Validated @Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto
  );

  @PostMapping("/login")
  ResponseEntity<ModelMap> login(
    @Validated @Valid @RequestBody UserLoginRequest userLoginRequest);

  @PostMapping("/refresh-token")
  void refreshToken(
    HttpServletRequest request,
    HttpServletResponse response
  );

  @PostMapping("/forgot-password")
  void forgotPassword(
    HttpServletRequest request,
    HttpServletResponse response
  );

  @PostMapping("/change-password")
  void changePassword(
    HttpServletRequest request,
    HttpServletResponse response
  );

  @PostMapping("/update")
  void updateProfile(
    HttpServletRequest request,
    HttpServletResponse response
  );

  @PostMapping("/delete")
  void deleteAccount(
    HttpServletRequest request,
    HttpServletResponse response
  );

}

