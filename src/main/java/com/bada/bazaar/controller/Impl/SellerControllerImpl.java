package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.SellerController;
import com.bada.bazaar.dto.request.SellerPutRequestDto;
import com.bada.bazaar.entity.User;
import com.bada.bazaar.dto.request.SellerPostRequestDto;
import com.bada.bazaar.dto.response.SellerResponseDto;
import com.bada.bazaar.service.SellerService;
import com.bada.bazaar.util.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SellerControllerImpl implements SellerController {

  private final SellerService sellerService;


  @Override
  public ResponseEntity<SellerResponseDto> getSellerById(
    @PathVariable Integer id, HttpServletRequest request) {
    User user = JwtHelper.getUserInfo(request);
    log.info("[GET]: Request to get seller by ID: {}", id);
    return ResponseEntity.ok().body(sellerService.getSellerById(id));
  }

  @Override
  public ResponseEntity<SellerResponseDto> updateSeller(
    @PathVariable Integer id,
    SellerPutRequestDto sellerPutRequestDto,
    HttpServletRequest request) {
    User user = JwtHelper.getUserInfo(request);
    log.info("[PUT]: Request to update seller by ID: {}", id);
    return ResponseEntity.status(HttpStatus.OK)
      .body(sellerService.updateSeller(id, sellerPutRequestDto));
  }

  @Override
  public ResponseEntity<ModelMap> deleteSeller(
    @PathVariable Integer id, HttpServletRequest request) {
    User user = JwtHelper.getUserInfo(request);
    log.info("[DELETE]: Request to delete seller by ID: {}", id);
    sellerService.deleteSeller(id);
    return ResponseEntity.status(HttpStatus.OK)
      .body(new ModelMap()
        .addAttribute("SellerId", id)
        .addAttribute("msg", "Successfully deleted.")
      );
  }

}
