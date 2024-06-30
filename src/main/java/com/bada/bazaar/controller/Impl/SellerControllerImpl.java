package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.SellerController;
import com.bada.bazaar.requestDto.SellerRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import com.bada.bazaar.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SellerControllerImpl implements SellerController {

  private final SellerService sellerService;

  @Override
  public ResponseEntity<Object> addSeller(SellerRequestDto sellerRequestDto) {
    log.info("[POST]: Request to add seller: {}", sellerRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(sellerService.addSeller(sellerRequestDto));
  }

  @Override
  public ResponseEntity<SellerResponseDto> getSellerById(Integer sellerId) {
    log.info("[GET]: Request to get seller by ID: {}", sellerId);
    return ResponseEntity.ok().body(sellerService.getSellerById(sellerId));
  }

  @Override
  public ResponseEntity<ModelMap> deleteSeller(Integer sellerId) {
    log.info("[DELETE]: Request to delete seller by ID: {}", sellerId);
    sellerService.deleteSeller(sellerId);
    return ResponseEntity.status(HttpStatus.OK)
      .body(new ModelMap().addAttribute("msg", "Successfully deleted."));
  }


}
