package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.SellerController;
import com.bada.bazaar.entity.UserEntity;
import com.bada.bazaar.requestDto.SellerPutRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import com.bada.bazaar.service.SellerService;
import com.bada.bazaar.util.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  public ResponseEntity<SellerResponseDto> getSellerById(HttpServletRequest request) {
    UserEntity user = CommonUtils.getUserInfo(request);
    log.info("[GET]: Request to get seller by ID: {}", user.getUserId());
    return ResponseEntity.ok().body(sellerService.getSellerById(user.getUserId()));
  }

  @Override
  public ResponseEntity<SellerResponseDto> updateSeller(
    SellerPutRequestDto sellerPutRequestDto, BindingResult bindingResult,
    HttpServletRequest request) {
    UserEntity user = CommonUtils.getUserInfo(request);
    log.info("[PUT]: Request to update seller by ID: {}", user.getUserId());
    return ResponseEntity.status(HttpStatus.OK)
      .body(sellerService.updateSeller(user.getUserId(), sellerPutRequestDto));
  }

  @Override
  public ResponseEntity<ModelMap> deleteSeller(HttpServletRequest request) {
    UserEntity user = CommonUtils.getUserInfo(request);
    log.info("[DELETE]: Request to delete seller by ID: {}", user.getUserId());
    sellerService.deleteSeller(user.getUserId());
    return ResponseEntity.status(HttpStatus.OK)
      .body(new ModelMap()
        .addAttribute("SellerId", user.getUserId())
        .addAttribute("msg", "Successfully deleted.")
      );
  }

}
