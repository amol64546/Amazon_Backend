package com.bada.bazaar.controller;

import com.bada.bazaar.requestDto.SellerRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import com.bada.bazaar.service.Imp.SellerServiceImp;
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

@RestController
@RequestMapping("/v1/sellers")
@Slf4j
@RequiredArgsConstructor
public class SellerController {

  private final SellerServiceImp sellerService;

  @PostMapping
  public ResponseEntity<Object> addSeller(@RequestBody SellerRequestDto sellerRequestDto) {
    log.info("[POST]: Request to add seller: {}", sellerRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(sellerService.addSeller(sellerRequestDto));
  }

  @GetMapping("{id}")
  public ResponseEntity<SellerResponseDto> getSellerById(@PathVariable Integer id) {
    log.info("[GET]: Request to get seller by ID: {}", id);
    return ResponseEntity.ok().body(sellerService.getSellerById(id));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteSeller(@PathVariable Integer id) {
    log.info("[DELETE]: Request to delete seller by ID: {}", id);
    sellerService.deleteSeller(id);
    return ResponseEntity.noContent().build();
  }

}
