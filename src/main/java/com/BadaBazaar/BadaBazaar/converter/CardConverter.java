package com.BadaBazaar.BadaBazaar.converter;

import com.BadaBazaar.BadaBazaar.model.Card;
import com.BadaBazaar.BadaBazaar.requestDto.CardRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.CardDto;
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

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiry(cardRequestDto.getExpiry())
                .build();
    }
}
