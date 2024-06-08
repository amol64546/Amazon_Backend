package com.BadaBazaar.BadaBazaar.service;

import com.BadaBazaar.BadaBazaar.requestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.SellerResponseDto;
import java.util.List;

public interface SellerService {

    SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    List<SellerResponseDto> getAllSellers();

    SellerResponseDto getSellerById(Integer sellerId);

    void deleteSeller(Integer id);
}
