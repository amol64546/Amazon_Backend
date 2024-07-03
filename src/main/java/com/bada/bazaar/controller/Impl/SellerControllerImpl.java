package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.SellerController;
import com.bada.bazaar.entity.User;
import com.bada.bazaar.requestDto.SellerPutRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import com.bada.bazaar.service.SellerService;
import com.bada.bazaar.util.CommonUtils;
import com.bada.bazaar.util.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
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
  public ResponseEntity<SellerResponseDto> getSellerById(HttpServletRequest request) {
    User user = JwtHelper.getUserInfo(request);
    log.info("[GET]: Request to get seller by ID: {}", user.getId());
    return ResponseEntity.ok().body(sellerService.getSellerById(user.getId()));
  }

  @Override
  public ResponseEntity<SellerResponseDto> updateSeller(
    SellerPutRequestDto sellerPutRequestDto,
    HttpServletRequest request) {
    User user = JwtHelper.getUserInfo(request);
    log.info("[PUT]: Request to update seller by ID: {}", user.getId());
    return ResponseEntity.status(HttpStatus.OK)
      .body(sellerService.updateSeller(user.getId(), sellerPutRequestDto));
  }

  @Override
  public ResponseEntity<ModelMap> deleteSeller(HttpServletRequest request) {
    User user = JwtHelper.getUserInfo(request);
    log.info("[DELETE]: Request to delete seller by ID: {}", user.getId());
    sellerService.deleteSeller(user.getId());
    return ResponseEntity.status(HttpStatus.OK)
      .body(new ModelMap()
        .addAttribute("SellerId", user.getId())
        .addAttribute("msg", "Successfully deleted.")
      );
  }

}
