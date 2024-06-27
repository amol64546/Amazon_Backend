package com.bada.bazaar.controller;

import com.bada.bazaar.requestDto.SellerRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/sellers")
public interface SellerController {

  @PostMapping
  ResponseEntity<Object> addSeller(@RequestBody SellerRequestDto sellerRequestDto) ;

  @GetMapping("{id}")
  ResponseEntity<SellerResponseDto> getSellerById(@PathVariable Integer id);

  @DeleteMapping("{id}")
  ResponseEntity<Void> deleteSeller(@PathVariable Integer id) ;

}

