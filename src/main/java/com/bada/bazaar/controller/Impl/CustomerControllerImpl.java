package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.CustomerController;
import com.bada.bazaar.dto.request.CustomerPutRequestDto;
import com.bada.bazaar.dto.response.CustomerResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerControllerImpl implements CustomerController {

  @Override
  public ResponseEntity<CustomerResponseDto> getCustomerById(
    Integer customerId,
    HttpServletRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<CustomerResponseDto> updateCustomer(
    Integer customerId,
    CustomerPutRequestDto sellerPutRequestDto,
    HttpServletRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<ModelMap> deleteCustomer(
    Integer customerId,
    HttpServletRequest request) {
    return null;
  }
}
