package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.BuyerRequestDto;
import com.bada.bazaar.responseDto.BuyerResponseDto;

public interface BuyerService {

  BuyerResponseDto addBuyer(BuyerRequestDto buyerRequestDto);

  BuyerResponseDto getBuyerById(Integer customerId);

  void deleteById(Integer customerId);

}
