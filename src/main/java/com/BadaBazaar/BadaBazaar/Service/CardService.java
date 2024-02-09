package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.RequestDto.CardRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.CardResponseDto;


public interface CardService {
    public CardResponseDto add(CardRequestDto cardRequestDto) throws Exception;

    void remove(int customerId, int cardId)throws Exception;

    CardResponseDto getAllCardsByCustomerId(int customerId) throws Exception;


}
