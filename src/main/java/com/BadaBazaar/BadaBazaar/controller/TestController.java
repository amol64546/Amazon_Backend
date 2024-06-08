package com.BadaBazaar.BadaBazaar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    log.info("TestController");
    return ResponseEntity.ok().body("TestController");
  }

}

