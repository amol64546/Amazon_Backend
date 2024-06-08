package com.BadaBazaar.BadaBazaar.service;

import com.BadaBazaar.BadaBazaar.requestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.SellerResponseDto;

import java.util.List;

public interface SellerService {

    Object addSeller(SellerRequestDto sellerRequestDto);

    List<SellerResponseDto> getAllSellers();

    SellerResponseDto getSellerByPan(String panNo);

    SellerResponseDto getSellerById(int sellerId);
}
