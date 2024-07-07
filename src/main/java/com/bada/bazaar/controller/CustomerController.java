package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.CustomerPutRequestDto;
import com.bada.bazaar.dto.response.CustomerResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Customer")
@RequestMapping("/v1/customers")
@PreAuthorize("hasRole('CUSTOMER')")
public interface CustomerController {

  @GetMapping("/{customerId}")
  ResponseEntity<CustomerResponseDto> getCustomerById(
    @PathVariable Integer customerId,
    HttpServletRequest request);

  @PutMapping("/{customerId}")
  ResponseEntity<CustomerResponseDto> updateCustomer(
    @PathVariable Integer customerId,
    @Validated @Valid @RequestBody CustomerPutRequestDto sellerPutRequestDto,
    HttpServletRequest request);

  @DeleteMapping("/{customerId}")
  ResponseEntity<ModelMap> deleteCustomer(
    @PathVariable Integer customerId,
    HttpServletRequest request);


}
