package com.bada.bazaar.controller;

import com.bada.bazaar.requestDto.SellerPostRequestDto;
import com.bada.bazaar.requestDto.SellerPutRequestDto;
import com.bada.bazaar.responseDto.SellerResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("v1/sellers")
@Validated
public interface SellerController {

  // ROLE - SELLER
  @PostMapping
  ResponseEntity<Object> registerSeller(@Validated @Valid @RequestBody SellerPostRequestDto sellerPostRequestDto,
  BindingResult bindingResult, HttpServletRequest request);

  @GetMapping("{sellerId}")
  ResponseEntity<SellerResponseDto> getSellerById(@PathVariable Integer sellerId, HttpServletRequest request);

  @PutMapping("{sellerId}")
  ResponseEntity<SellerResponseDto> updateSeller(@PathVariable Integer sellerId,
    @Validated @Valid @RequestBody SellerPutRequestDto sellerPutRequestDto,
    BindingResult bindingResult,
    HttpServletRequest request);

  @DeleteMapping("{sellerId}")
  ResponseEntity<ModelMap> deleteSeller(@PathVariable Integer sellerId, HttpServletRequest request);

  // ROLE - ADMIN
  @GetMapping
  ResponseEntity<List<SellerResponseDto>> getAllSellers(
    @Parameter(hidden = true) @PageableDefault(sort = "dateJoined",
      direction = Sort.Direction.DESC) Pageable pageable,
    HttpServletRequest request);


}

