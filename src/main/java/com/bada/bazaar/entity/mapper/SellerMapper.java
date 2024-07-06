package com.bada.bazaar.entity.mapper;


import com.bada.bazaar.entity.Seller;
import com.bada.bazaar.dto.request.SellerPutRequestDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SellerMapper {

//  @Mapping(target = "updatedTimeMs", expression = "java(new java.util.Date().getTime())")
  @Mapping(target = "id", ignore = true)
  Seller updateSeller(SellerPutRequestDto sellerPutRequestDto,
    @MappingTarget Seller seller);

}
