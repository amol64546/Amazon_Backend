package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.Model.Seller;
import com.BadaBazaar.BadaBazaar.RequestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.SellerResponseDto;

import java.util.List;

public interface SellerService {

    String addSeller(SellerRequestDto sellerRequestDto);
    List<SellerResponseDto> getAllSellers();

    SellerResponseDto getSellerByPan(String panNo);

    SellerResponseDto getSellerById(int sellerId);
}
