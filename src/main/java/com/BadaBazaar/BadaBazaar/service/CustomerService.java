package com.BadaBazaar.BadaBazaar.service;

import com.BadaBazaar.BadaBazaar.requestDto.CustomerRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

     String addCustomer(CustomerRequestDto customerRequestDto);

     List<CustomerResponseDto> getAll();

    CustomerResponseDto getCustomerById(int customerId);

    void deleteById(int customerId);

    CustomerResponseDto getCustomerByEmail(String email);

    CustomerResponseDto updateMobNo(int customerId,String mobNo);

    CustomerResponseDto updateEmail(int customerId, String email);
}
