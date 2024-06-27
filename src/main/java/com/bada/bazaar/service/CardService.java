package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.CardRequestDto;
import com.bada.bazaar.responseDto.CustomerCardResponseDto;


public interface CardService {

    CustomerCardResponseDto add(CardRequestDto cardRequestDto) throws Exception;

    void remove(int customerId, int cardId)throws Exception;

    CustomerCardResponseDto getAllCardsByCustomerId(int customerId) throws Exception;


}
