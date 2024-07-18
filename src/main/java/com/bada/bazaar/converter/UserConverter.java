package com.bada.bazaar.converter;

import com.bada.bazaar.dto.request.UserRegisterRequestDto;
import com.bada.bazaar.dto.response.UserResponseDto;
import com.bada.bazaar.entity.Customer;
import com.bada.bazaar.entity.Seller;
import com.bada.bazaar.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
  injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserConverter {

  User userRegisterRequestDtoToUser(UserRegisterRequestDto userRegisterRequestDto);

  Seller userRegisterRequestDtoToSeller(UserRegisterRequestDto userRegisterRequestDto);

  Customer userRegisterRequestDtoToCustomer(UserRegisterRequestDto userRegisterRequestDto);

  UserResponseDto sellerToUserResponseDto(Seller seller);

  UserResponseDto customerToUserResponseDto(Customer customerFromDb);
}
