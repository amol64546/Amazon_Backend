package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.Repository.CustomerRepository;
import com.BadaBazaar.BadaBazaar.RequestDto.CustomerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;

public interface CustomerService {

    public String addCustomer(CustomerRequestDto customerRequestDto);
}
