package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.CustomerController;
import com.bada.bazaar.requestDto.CustomerPutRequestDto;
import com.bada.bazaar.responseDto.CustomerResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerControllerImpl implements CustomerController {

  @Override
  public ResponseEntity<CustomerResponseDto> getCustomerById(
    HttpServletRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<CustomerResponseDto> updateCustomer(
    CustomerPutRequestDto sellerPutRequestDto, BindingResult bindingResult,
    HttpServletRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<ModelMap> deleteCustomer( HttpServletRequest request) {
    return null;
  }
}
