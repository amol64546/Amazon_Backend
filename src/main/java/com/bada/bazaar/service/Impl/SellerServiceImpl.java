package com.bada.bazaar.service.Impl;

import com.bada.bazaar.cache.SellerCache;
import com.bada.bazaar.dto.request.SellerPutRequestDto;
import com.bada.bazaar.dto.response.SellerResponseDto;
import com.bada.bazaar.entity.Seller;
import com.bada.bazaar.repository.SellerRepository;
import com.bada.bazaar.service.SellerService;
import com.bada.bazaar.util.CommonServices;
import java.util.Date;
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
  private final SellerRepository sellerRepository;

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
    return sellerRepository.findAll(pageable)
      .map(seller -> modelMapper.map(seller, SellerResponseDto.class))
      .toList();
  }

  @Override
  public SellerResponseDto updateSeller(Integer sellerId, SellerPutRequestDto sellerPutRequestDto) {
    Seller seller = sellerCache.getSeller(sellerId);
    modelMapper.map(sellerPutRequestDto, seller);
    seller.setLastModifiedDate(new Date());
    Seller updatedSeller = sellerCache.saveSeller(seller);
    return modelMapper.map(updatedSeller, SellerResponseDto.class);
  }


}
