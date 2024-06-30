package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.SellerPostRequestDto;
import com.bada.bazaar.requestDto.SellerPutRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface SellerService {

//    UserDetails getSellerByUsername(String username);

  SellerResponseDto registerSeller(SellerPostRequestDto sellerPostRequestDto);

  SellerResponseDto getSellerById(Integer sellerId);

  void deleteSeller(Integer sellerId);

  List<SellerResponseDto> retrieveAllSellers(Pageable pageable);

  SellerResponseDto updateSeller(Integer sellerId, SellerPutRequestDto sellerPutRequestDto);
}
