package com.BadaBazaar.BadaBazaar.service;

import com.BadaBazaar.BadaBazaar.requestDto.CardRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.CardResponseDto;


public interface CardService {

    CardResponseDto add(CardRequestDto cardRequestDto) throws Exception;

    void remove(int customerId, int cardId)throws Exception;

    CardResponseDto getAllCardsByCustomerId(int customerId) throws Exception;


}
