package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.SellerController;
import com.bada.bazaar.requestDto.SellerPostRequestDto;
import com.bada.bazaar.requestDto.SellerPutRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import com.bada.bazaar.service.SellerService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SellerControllerImpl implements SellerController {

  private final SellerService sellerService;

  @Override
  public ResponseEntity<Object> registerSeller(SellerPostRequestDto sellerPostRequestDto,
    BindingResult bindingResult, HttpServletRequest request) {
    log.info("[POST]: Request to add seller: {}", sellerPostRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(sellerService.registerSeller(sellerPostRequestDto));
  }

  @Override
  public ResponseEntity<SellerResponseDto> getSellerById(Integer sellerId, HttpServletRequest request) {
    log.info("[GET]: Request to get seller by ID: {}", sellerId);
    return ResponseEntity.ok().body(sellerService.getSellerById(sellerId));
  }

  @Override
  public ResponseEntity<SellerResponseDto> updateSeller(Integer sellerId,
    SellerPutRequestDto sellerPutRequestDto, BindingResult bindingResult,
    HttpServletRequest request) {
    log.info("[PUT]: Request to update seller by ID: {}", sellerId);
    return ResponseEntity.status(HttpStatus.OK)
      .body(sellerService.updateSeller(sellerId, sellerPutRequestDto));
  }

  @Override
  public ResponseEntity<ModelMap> deleteSeller(Integer sellerId, HttpServletRequest request) {
    log.info("[DELETE]: Request to delete seller by ID: {}", sellerId);
    sellerService.deleteSeller(sellerId);
    return ResponseEntity.status(HttpStatus.OK)
      .body(new ModelMap()
        .addAttribute("SellerId", sellerId)
        .addAttribute("msg", "Successfully deleted.")
      );
  }

  @Override
  public ResponseEntity<List<SellerResponseDto>> getAllSellers(Pageable pageable,
    HttpServletRequest request) {
    log.info("[GET]: Request to get all sellers.");
    return ResponseEntity.ok().body(sellerService.retrieveAllSellers(pageable));
  }


}
