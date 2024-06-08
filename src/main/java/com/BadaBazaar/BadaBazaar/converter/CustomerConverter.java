package com.BadaBazaar.BadaBazaar.converter;

import com.BadaBazaar.BadaBazaar.model.Customer;
import com.BadaBazaar.BadaBazaar.requestDto.CustomerRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.CustomerResponseDto;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@Builder
@UtilityClass
public class CustomerConverter {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .email(customerRequestDto.getEmail())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .mobNo(customer.getMobNo())
                .email(customer.getEmail())
                .build();
    }
}
