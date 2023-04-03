package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.Model.Customer;
import com.BadaBazaar.BadaBazaar.Repository.CustomerRepository;
import com.BadaBazaar.BadaBazaar.RequestDto.CustomerRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CustomerService {

     String addCustomer(CustomerRequestDto customerRequestDto);

     List<CustomerResponseDto> getAll();

    CustomerResponseDto getCustomerById(int customerId);

    void deleteById(int customerId);

    CustomerResponseDto getCustomerByEmail(String email);

    CustomerResponseDto updateMobNo(int customerId,String mobNo);
}
