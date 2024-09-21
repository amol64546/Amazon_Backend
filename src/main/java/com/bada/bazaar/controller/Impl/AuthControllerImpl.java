package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.AuthController;
import com.bada.bazaar.dto.request.UserLoginRequest;
import com.bada.bazaar.dto.request.UserRegisterRequestDto;
import com.bada.bazaar.dto.response.UserResponseDto;
import com.bada.bazaar.service.AuthService;
import com.bada.bazaar.util.CommonServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

  private final AuthService authService;
  private final CommonServices commonServices;

  @Override
  public ResponseEntity<UserResponseDto> register(
    UserRegisterRequestDto userRegisterRequestDto
  ) {
    log.info("[POST]: Request to register user: {}",
      commonServices.prettyPrint(userRegisterRequestDto));
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(authService.register(userRegisterRequestDto));
  }

  @Override
  public ResponseEntity<ModelMap> login(UserLoginRequest userLoginRequest) {
    log.info("[POST]: Request to login user: {}",
      commonServices.prettyPrint(userLoginRequest));
    String token = authService.login(userLoginRequest);
    return ResponseEntity.ok()
      .body(new ModelMap().addAttribute("token", token));
  }

}

