package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.AdminController;
import com.bada.bazaar.responseDto.CustomerResponseDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import com.bada.bazaar.service.CustomerService;
import com.bada.bazaar.service.SellerService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminControllerImpl implements AdminController {

  private final SellerService sellerService;
  private final CustomerService customerService;

  @Override
  public ResponseEntity<List<SellerResponseDto>> getAllSellers(Pageable pageable,
    HttpServletRequest request) {
    log.info("[GET]: Request to get all sellers.");
    return ResponseEntity.ok().body(sellerService.retrieveAllSellers(pageable));
  }

  @Override
  public ResponseEntity<List<CustomerResponseDto>> getAllCustomers(Pageable pageable,
    HttpServletRequest request) {
    log.info("[GET]: Request to get all customers.");
    return ResponseEntity.ok().body(customerService.retrieveAllCustomers(pageable));
  }

}

