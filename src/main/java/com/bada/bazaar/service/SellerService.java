package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.SellerRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface SellerService {

    UserDetails loadUserByUsername(String username);

    SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    List<SellerResponseDto> getAllSellers();

    SellerResponseDto getSellerById(Integer sellerId);

    void deleteSeller(Integer id);

  Object updateSeller(SellerRequestDto sellerRequestDto);
}
