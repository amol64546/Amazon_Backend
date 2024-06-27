package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.BuyerRequestDto;
import com.bada.bazaar.responseDto.CustomerResponseDto;

public interface CustomerService {

  CustomerResponseDto addBuyer(BuyerRequestDto buyerRequestDto);

  CustomerResponseDto getBuyerById(Integer customerId);

  void deleteById(Integer customerId);

}
