package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.Model.Customer;
import com.BadaBazaar.BadaBazaar.Repository.CustomerRepository;
import com.BadaBazaar.BadaBazaar.RequestDto.CustomerRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CustomerService {

    public String addCustomer(CustomerRequestDto customerRequestDto);

    public List<CustomerResponseDto> getAll();
}
