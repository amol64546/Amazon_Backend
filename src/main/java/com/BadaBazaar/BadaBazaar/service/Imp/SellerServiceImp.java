package com.BadaBazaar.BadaBazaar.service.Imp;

import com.BadaBazaar.BadaBazaar.exception.SellerNotFoundException;
import com.BadaBazaar.BadaBazaar.model.Seller;
import com.BadaBazaar.BadaBazaar.repository.SellerRepository;
import com.BadaBazaar.BadaBazaar.requestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.SellerResponseDto;
import com.BadaBazaar.BadaBazaar.service.SellerService;
import com.BadaBazaar.BadaBazaar.util.SellerCacheUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SellerServiceImp implements SellerService {

  private final ModelMapper modelMapper;

  private final SellerCacheUtil sellerCacheUtil;

  private final SellerRepository sellerRepository;

  @Override
  public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
    Seller seller = modelMapper.map(sellerRequestDto, Seller.class);
    Seller savedSeller = sellerCacheUtil.saveSeller(seller);
    return modelMapper.map(savedSeller, SellerResponseDto.class);
  }

  @Override
  public List<SellerResponseDto> getAllSellers() {
    List<Seller> sellers = sellerCacheUtil.getAllSellers();

    return sellers.stream()
      .map(seller -> modelMapper.map(seller, SellerResponseDto.class))
      .toList();
  }


  @Override
  public SellerResponseDto getSellerById(Integer sellerId) {
    Seller seller = sellerCacheUtil.getSeller(sellerId);
    return modelMapper.map(seller, SellerResponseDto.class);
  }

  @Override
  public void deleteSeller(Integer sellerId) {
//    sellerCacheUtil.getSeller(sellerId); // check if seller exists
    if (!sellerRepository.existsById(sellerId)) {
        throw new SellerNotFoundException();
    }
    sellerCacheUtil.deleteSeller(sellerId);

  }


}
