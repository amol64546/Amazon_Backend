package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.RequestDto.CardRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.CardResponseDto;

import javax.lang.model.type.ExecutableType;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws Exception;
}
