package com.BadaBazaar.BadaBazaar.Converter;

import com.BadaBazaar.BadaBazaar.Model.Card;
import com.BadaBazaar.BadaBazaar.ResponseDto.CardDto;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@Builder
@UtilityClass
public class CardConverter {

    public static CardDto cardToCardDto(Card card){
        return CardDto.builder()
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .cvv(card.getCvv())
                .expiry(card.getExpiry())
                .build();
    }
}
