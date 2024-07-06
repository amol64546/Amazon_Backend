package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.SellerPutRequestDto;
import com.bada.bazaar.dto.response.SellerResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/sellers")
public interface SellerController {

  @GetMapping("/{id}")
  ResponseEntity<SellerResponseDto> getSellerById(
    @PathVariable Integer id, HttpServletRequest request);

  @PutMapping("/{id}")
  ResponseEntity<SellerResponseDto> updateSeller(
    @PathVariable Integer id,
    @Validated @Valid @RequestBody SellerPutRequestDto sellerPutRequestDto,
    HttpServletRequest request);

  @DeleteMapping("/{id}")
  ResponseEntity<ModelMap> deleteSeller(
    @PathVariable Integer id,
    HttpServletRequest request);

}

