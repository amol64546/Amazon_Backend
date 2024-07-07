package com.bada.bazaar.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Converter
@RequiredArgsConstructor
public class AttributeEncryptor implements AttributeConverter<String, String> {


  @Override
  public String convertToDatabaseColumn(String attribute) {
    return null;
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    return null;
  }
}