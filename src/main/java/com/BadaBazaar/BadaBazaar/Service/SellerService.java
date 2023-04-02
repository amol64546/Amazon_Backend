package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.Model.Seller;
import com.BadaBazaar.BadaBazaar.RequestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.SellerResponseDto;

import java.util.List;

public interface SellerService {

    public String addSeller(SellerRequestDto sellerRequestDto);
    public List<SellerResponseDto> getAllSellers();

    public SellerResponseDto getSellerByPan(String panNo);
}
