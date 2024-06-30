package com.bada.bazaar.service.Impl;

import com.bada.bazaar.cache.SellerCache;
import com.bada.bazaar.entity.Seller;
import com.bada.bazaar.requestDto.SellerPutRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import com.bada.bazaar.service.SellerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SellerServiceImpl implements SellerService {

  private final ModelMapper modelMapper;
  private final SellerCache sellerCache;

  @Override
  public SellerResponseDto getSellerById(Integer sellerId) {
    Seller seller = sellerCache.getSeller(sellerId);
    return modelMapper.map(seller, SellerResponseDto.class);
  }

  @Override
  public void deleteSeller(Integer sellerId) {
    sellerCache.getSeller(sellerId); // check if seller exists
    sellerCache.deleteSeller(sellerId);

  }

  @Override
  public List<SellerResponseDto> retrieveAllSellers(Pageable pageable) {
    return sellerCache.retrieveAllSellers(pageable)
      .map(seller -> modelMapper.map(seller, SellerResponseDto.class))
      .toList();
  }

  @Override
  public SellerResponseDto updateSeller(Integer sellerId, SellerPutRequestDto sellerPutRequestDto) {
    Seller seller = sellerCache.getSeller(sellerId);
    modelMapper.map(sellerPutRequestDto, seller);
    Seller updatedSeller = sellerCache.saveSeller(seller);
    return modelMapper.map(updatedSeller, SellerResponseDto.class);
  }

}
