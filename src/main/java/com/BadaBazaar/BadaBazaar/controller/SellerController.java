package com.BadaBazaar.BadaBazaar.controller;

import com.BadaBazaar.BadaBazaar.requestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.SellerResponseDto;
import com.BadaBazaar.BadaBazaar.service.Imp.SellerServiceImp;
import java.util.List;
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
@RequestMapping("/sellers")
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

  @GetMapping
  public ResponseEntity<List<SellerResponseDto>> getAllSellers() {
    log.info("[GET]: Request to get all sellers");
    return ResponseEntity.ok().body(sellerService.getAllSellers());
  }

  @GetMapping("{sellerId}")
  public ResponseEntity<SellerResponseDto> getSellerById(@PathVariable Integer sellerId) {
    log.info("[GET]: Request to get seller by ID: {}", sellerId);
    return ResponseEntity.ok().body(sellerService.getSellerById(sellerId));
  }

  @DeleteMapping("{sellerId}")
  public ResponseEntity<Void> deleteSeller(@PathVariable Integer sellerId) {
    log.info("[DELETE]: Request to delete seller by ID: {}", sellerId);
    sellerService.deleteSeller(sellerId);
    return ResponseEntity.noContent().build();
  }

}
