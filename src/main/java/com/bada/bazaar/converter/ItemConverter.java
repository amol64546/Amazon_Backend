package com.bada.bazaar.converter;

import com.bada.bazaar.dto.response.ItemResponseDto;
import com.bada.bazaar.entity.Item;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
  injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ItemConverter {
  ItemResponseDto itemToItemResponseDto(Item item);
}
