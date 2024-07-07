package com.bada.bazaar.service.Impl;

import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.dto.response.CustomerResponseDto;
import com.bada.bazaar.service.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

  private final ModelMapper modelMapper;
  private final CustomerRepository customerRepository;

  @Override
  public List<CustomerResponseDto> retrieveAllCustomers(Pageable pageable) {
    return customerRepository.findAll(pageable)
      .map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
      .toList();
  }

}
