package com.bada.bazaar.converter;

import com.bada.bazaar.dto.request.ProductPostRequestDto;
import com.bada.bazaar.dto.request.ProductPutRequestDto;
import com.bada.bazaar.dto.response.ProductResponseDto;
import com.bada.bazaar.entity.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
  injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductConverter {
  
  Product productPostRequestDtoToProduct(ProductPostRequestDto productPostRequestDto);


  ProductResponseDto productToProductResponseDto(Product product);

  @Mapping(target = "id", ignore = true)
  Product productPutRequestDtoToProduct(ProductPutRequestDto productPutRequestDto);
}
