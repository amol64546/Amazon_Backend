package com.bada.bazaar.converter;

import com.bada.bazaar.dto.request.CardRequestDto;
import com.bada.bazaar.dto.response.CardResponseDto;
import com.bada.bazaar.entity.Card;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
  injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CardConverter {

  Card cardRequestDtoToCard(CardRequestDto cardRequestDto);

  CardResponseDto cardToCardResponseDto(Card card);
}
