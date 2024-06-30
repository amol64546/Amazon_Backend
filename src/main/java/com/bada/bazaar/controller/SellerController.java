package com.bada.bazaar.controller;

import com.bada.bazaar.requestDto.SellerRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("v1/sellers")
@Validated
public interface SellerController {

  @PostMapping
  ResponseEntity<Object> registerSeller(@Valid @RequestBody SellerRequestDto sellerRequestDto) ;

  @GetMapping("{sellerId}")
  ResponseEntity<SellerResponseDto> getSellerById(@PathVariable Integer sellerId);

  @DeleteMapping("{sellerId}")
  ResponseEntity<ModelMap> deleteSeller(@PathVariable Integer sellerId) ;

}

