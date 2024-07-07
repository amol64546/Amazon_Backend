package com.bada.bazaar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition
@EnableWebSecurity
@EnableMethodSecurity
public class BadaBazaarApplication {

  public static void main(String[] args) {
    SpringApplication.run(BadaBazaarApplication.class, args);
  }

}

