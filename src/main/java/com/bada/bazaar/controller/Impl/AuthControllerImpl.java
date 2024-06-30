package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.AuthController;
import com.bada.bazaar.requestDto.LoginRequest;
import com.bada.bazaar.requestDto.UserPostRequestDto;
import com.bada.bazaar.responseDto.UserResponseDto;
import com.bada.bazaar.service.AuthService;
import com.bada.bazaar.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

  private final AuthService authService;

  @Override
  public ResponseEntity<UserResponseDto> register(
    UserPostRequestDto userPostRequestDto
  ) {
    log.info("[POST]: Request to add seller: {}",
      CommonUtils.prettyPrint(userPostRequestDto));
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(authService.register(userPostRequestDto));
  }

  @Override
  public ResponseEntity<String> login(LoginRequest loginRequest) {
    return ResponseEntity.ok().body(authService.login(loginRequest));
  }

}

