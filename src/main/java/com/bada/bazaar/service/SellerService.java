package com.bada.bazaar.service;

import com.bada.bazaar.dto.request.SellerPutRequestDto;
import com.bada.bazaar.dto.response.SellerResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface SellerService {

  SellerResponseDto getSellerById(Integer sellerId);

  void deleteSeller(Integer sellerId);

  List<SellerResponseDto> retrieveAllSellers(Pageable pageable);

  SellerResponseDto updateSeller(Integer sellerId, SellerPutRequestDto sellerPutRequestDto);

}
