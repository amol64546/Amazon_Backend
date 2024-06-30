package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.SellerRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;

public interface SellerService {

//    UserDetails getSellerByUsername(String username);

    SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    SellerResponseDto getSellerById(Integer sellerId);

    void deleteSeller(Integer sellerId);

}
