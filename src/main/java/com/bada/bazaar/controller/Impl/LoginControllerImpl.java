//package com.bada.bazaar.controller.Impl;
//
//import com.bada.bazaar.controller.LoginController;
//import com.bada.bazaar.requestDto.LoginRequest;
//import com.bada.bazaar.util.JwtHelper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//public class LoginControllerImpl implements LoginController {
//
//  private final AuthenticationManager authenticationManager;
//
//
//  @Override
//  public String login(LoginRequest loginRequest) {
//
//    authenticationManager.authenticate(
//      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//    return JwtHelper.generateToken(loginRequest.getUsername());
//
//  }
//
//  @GetMapping("/test")
//  public String test(@RequestHeader("Authorization") String token){
//    return "Hello World!";
//  }
//
//}
//
