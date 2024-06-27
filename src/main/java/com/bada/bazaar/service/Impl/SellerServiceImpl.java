package com.bada.bazaar.service.Impl;

import com.bada.bazaar.model.Seller;
import com.bada.bazaar.repository.SellerRepository;
import com.bada.bazaar.requestDto.SellerRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import com.bada.bazaar.service.SellerService;
import com.bada.bazaar.util.SellerCacheUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
//public class SellerServiceImpl implements SellerService, org.springframework.security.core.userdetails.UserDetailsService{
public class SellerServiceImpl implements SellerService{

  private final ModelMapper modelMapper;

  private final SellerCacheUtil sellerCacheUtil;

  private final SellerRepository sellerRepository;

//  private final PasswordEncoder passwordEncoder;
//
//  @Override
//  public UserDetails loadUserByUsername(String username) {
//    Seller seller = sellerRepository.findByUsername(username);
//    return org.springframework.security.core.userdetails.User.builder()
//      .username(seller.getUsername())
//      .password(seller.getPassword())
//      .build();
//  }

  @Override
  public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
    Seller seller = modelMapper.map(sellerRequestDto, Seller.class);
//    seller.setPassword(passwordEncoder.encode(sellerRequestDto.getPassword()));
    Seller savedSeller = sellerCacheUtil.saveSeller(seller);
    return modelMapper.map(savedSeller, SellerResponseDto.class);
  }


  @Override
  public SellerResponseDto getSellerById(Integer sellerId) {
    Seller seller = sellerCacheUtil.getSeller(sellerId);
    return modelMapper.map(seller, SellerResponseDto.class);
  }

  @Override
  public void deleteSeller(Integer sellerId) {
    sellerCacheUtil.getSeller(sellerId); // check if seller exists
    sellerCacheUtil.deleteSeller(sellerId);

  }


}
