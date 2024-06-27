package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.SellerController;
import com.bada.bazaar.requestDto.SellerRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import com.bada.bazaar.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<SellerResponseDto> getSellerById(Integer id) {
    log.info("[GET]: Request to get seller by ID: {}", id);
    return ResponseEntity.ok().body(sellerService.getSellerById(id));
  }

  @Override
  public ResponseEntity<Void> deleteSeller(Integer id) {
    log.info("[DELETE]: Request to delete seller by ID: {}", id);
    sellerService.deleteSeller(id);
    return ResponseEntity.noContent().build();
  }

}
