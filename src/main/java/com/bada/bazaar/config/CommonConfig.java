package com.bada.bazaar.config;

import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    // Set the matching strategy
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    // Create a condition that ignores null values
    Condition<?, ?> skipNulls = Conditions.isNotNull();

    // Apply the condition globally
    modelMapper.getConfiguration().setPropertyCondition(skipNulls);

    return modelMapper;
  }

}

