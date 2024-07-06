package com.bada.bazaar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition
public class BadaBazaarApplication {

  public static void main(String[] args) {
    SpringApplication.run(BadaBazaarApplication.class, args);
  }

}

