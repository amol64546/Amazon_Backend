package com.bada.bazaar.converter;

import com.bada.bazaar.dto.response.OrderResponseDto;
import com.bada.bazaar.entity.OrderEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
  injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderConverter {
  OrderResponseDto orderToOrderResponseDto(OrderEntity order);
}
