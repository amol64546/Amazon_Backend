package com.bada.bazaar.controller;

import com.bada.bazaar.responseDto.CustomerResponseDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("v1/admins")
public interface AdminController {

  @GetMapping("sellers")
  ResponseEntity<List<SellerResponseDto>> getAllSellers(
    @Parameter(hidden = true) @PageableDefault(sort = "dateJoined",
      direction = Sort.Direction.DESC) Pageable pageable,
    HttpServletRequest request);


  @GetMapping("customers")
  ResponseEntity<List<CustomerResponseDto>> getAllCustomers(
    @Parameter(hidden = true) @PageableDefault(sort = "dateJoined",
      direction = Sort.Direction.DESC) Pageable pageable,
    HttpServletRequest request);


}
