package com.BadaBazaar.BadaBazaar.Converter;

import com.BadaBazaar.BadaBazaar.Model.Seller;
import com.BadaBazaar.BadaBazaar.RequestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.SellerResponseDto;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@UtilityClass
@Builder
public class SellerConverter {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .email(sellerRequestDto.getEmail())
                .panNo(sellerRequestDto.getPanNo())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .email(seller.getEmail())
                .panNo(seller.getPanNo())
                .mobNo(seller.getMobNo())
                .build();
    }
}
