package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.requestDto.BuyerRequestDto;
import com.bada.bazaar.responseDto.CustomerResponseDto;
import com.bada.bazaar.service.Impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerControllerImpl {

  private final CustomerServiceImpl buyerService;

  @PostMapping
  public ResponseEntity<CustomerResponseDto> addBuyer(
    @Valid @Validated @RequestBody BuyerRequestDto buyerRequestDto) {
    log.info("[POST]: Request to add buyer: {}", buyerRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(buyerService.addBuyer(
      buyerRequestDto));
  }

  @GetMapping("{id}")
  public ResponseEntity<CustomerResponseDto> getBuyerById(@PathVariable Integer id) {
    log.info("[GET]: Request to get buyer by ID: {}", id);
    return ResponseEntity.status(HttpStatus.OK).body(buyerService.getBuyerById(id));
  }


  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteById(@PathVariable Integer id) {
    log.info("[DELETE]: Request to delete buyer by ID: {}", id);
    buyerService.deleteById(id);
    return ResponseEntity.ok().body("Buyer deleted successfully");
  }

}
