package com.bada.bazaar.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Converter
@RequiredArgsConstructor
public class AttributeEncryptor implements AttributeConverter<String, String> {

  private final PasswordEncoder passwordEncoder;

  @Override
  public String convertToDatabaseColumn(String attribute) {
    return passwordEncoder.encode(attribute);
  }

  //Todo: Implement this method
  @Override
  public String convertToEntityAttribute(String dbData) {
//    return passwordEncoder.encode(dbData);
    return dbData;
  }
}