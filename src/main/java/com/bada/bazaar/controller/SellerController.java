package com.bada.bazaar.controller;

import com.bada.bazaar.requestDto.SellerPutRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/sellers")
public interface SellerController {

  @GetMapping
  ResponseEntity<SellerResponseDto> getSellerById(HttpServletRequest request);

  @PutMapping
  ResponseEntity<SellerResponseDto> updateSeller(
    @Validated @Valid @RequestBody SellerPutRequestDto sellerPutRequestDto,
    HttpServletRequest request);

  @DeleteMapping
  ResponseEntity<ModelMap> deleteSeller(HttpServletRequest request);

}

