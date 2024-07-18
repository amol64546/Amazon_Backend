package com.bada.bazaar.converter;

import com.bada.bazaar.dto.response.CustomerResponseDto;
import com.bada.bazaar.entity.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
  injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerConverter {

  CustomerResponseDto customerToCustomerResponseDto(Customer customer);

}
