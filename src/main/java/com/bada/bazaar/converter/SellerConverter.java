package com.bada.bazaar.converter;

import com.bada.bazaar.dto.request.SellerPostRequestDto;
import com.bada.bazaar.dto.request.SellerPutRequestDto;
import com.bada.bazaar.dto.response.SellerResponseDto;
import com.bada.bazaar.entity.Seller;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
  injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SellerConverter {

  Seller sellerPostRequestDtoToSeller(SellerPostRequestDto sellerPostRequestDto);

  @Mapping(target = "id", ignore = true)
  void sellerPutRequestToSeller(SellerPutRequestDto sellerPutRequestDto,
    @MappingTarget Seller seller);

  SellerResponseDto sellerToSellerResponseDto(Seller seller);


}

