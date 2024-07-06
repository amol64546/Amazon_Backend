package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.SellerPutRequestDto;
import com.bada.bazaar.dto.response.SellerResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Seller")
@RequestMapping("/v1/sellers")
public interface SellerController {

  @GetMapping("/{sellerId}")
  ResponseEntity<SellerResponseDto> getSellerById(
    @PathVariable Integer sellerId, HttpServletRequest request);

  @PutMapping("/{sellerId}")
  ResponseEntity<SellerResponseDto> updateSeller(
    @PathVariable Integer sellerId,
    @Validated @Valid @RequestBody SellerPutRequestDto sellerPutRequestDto,
    HttpServletRequest request);

  @DeleteMapping("/{sellerId}")
  ResponseEntity<ModelMap> deleteSeller(
    @PathVariable Integer sellerId,
    HttpServletRequest request);

}

