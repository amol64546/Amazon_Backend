package com.bada.bazaar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }


  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}

