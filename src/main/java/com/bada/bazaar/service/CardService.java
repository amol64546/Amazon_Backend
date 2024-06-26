package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.CardRequestDto;
import com.bada.bazaar.responseDto.BuyerCardResponseDto;


public interface CardService {

    BuyerCardResponseDto add(CardRequestDto cardRequestDto) throws Exception;

    void remove(int customerId, int cardId)throws Exception;

    BuyerCardResponseDto getAllCardsByCustomerId(int customerId) throws Exception;


}
