package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.CustomerPutRequestDto;
import com.bada.bazaar.dto.response.CustomerResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/customers")
public interface CustomerController {

  @GetMapping
  ResponseEntity<CustomerResponseDto> getCustomerById(
    HttpServletRequest request);

  @PutMapping
  ResponseEntity<CustomerResponseDto> updateCustomer(
    @Validated @Valid @RequestBody CustomerPutRequestDto sellerPutRequestDto,
    HttpServletRequest request);

  @DeleteMapping
  ResponseEntity<ModelMap> deleteCustomer(
    HttpServletRequest request);


}
