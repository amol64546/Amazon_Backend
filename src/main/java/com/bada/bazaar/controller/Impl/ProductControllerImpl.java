package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.ProductController;
import com.bada.bazaar.model.UserInfo;
import com.bada.bazaar.requestDto.ProductPostRequestDto;
import com.bada.bazaar.requestDto.ProductPutRequestDto;
import com.bada.bazaar.responseDto.ProductResponseDto;
import com.bada.bazaar.service.ProductService;
import com.bada.bazaar.util.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j

public class ProductControllerImpl implements ProductController {

  private final ProductService productService;

  @Override
  @PreAuthorize("hasRole('SELLER')")
  public ResponseEntity<ProductResponseDto> addProductBySellerId(ProductPostRequestDto productPostRequestDto,
    BindingResult bindingResult, HttpServletRequest request) {
    log.info("[POST]: Request to add product: {}",
      CommonUtils.prettyPrint(productPostRequestDto));
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(productService.addProductBySellerId(productPostRequestDto, request));
  }

  @Override
  @PreAuthorize("hasRole('SELLER')")
  public ResponseEntity<ProductResponseDto> updateProduct(Integer productId,
    ProductPutRequestDto productPutRequestDto, BindingResult bindingResult,
    HttpServletRequest request) {
    log.info("[PUT]: Request to update product by ID: {}", productId);
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.updateProduct(productId, productPutRequestDto, request));
  }

  @Override
  @PreAuthorize("hasRole('SELLER')")
  public ResponseEntity<ModelMap> deleteProduct(Integer productId, HttpServletRequest request) {
    log.info("[DELETE]: Request to delete product by ID: {}", productId);
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.deleteProduct(productId, request));
  }

  @Override
  @PreAuthorize("hasRole('SELLER') || hasRole('CUSTOMER')")
  public ResponseEntity<ProductResponseDto> getProductById(Integer productId,
    HttpServletRequest request) {
    log.info("[GET]: Request to get product by ID: {}", productId);
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.getProductById(productId, request));
  }

  @Override
  @PreAuthorize("hasRole('SELLER') || hasRole('CUSTOMER')")
  public ResponseEntity<List<ProductResponseDto>> getProductsBySellerId(
    Pageable pageable, HttpServletRequest request) {
    UserInfo user = CommonUtils.getUserInfo(request);
    log.info("[GET]: Request to get products by seller ID: {}", user.getUserId());
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.getProductsBySellerId(user.getUserId(), pageable, request));
  }

  @Override
  public ResponseEntity<List<ProductResponseDto>> getAllProducts(Pageable pageable,
    HttpServletRequest request) {
    log.info("[GET]: Request to get all products.");
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.getAllProducts(pageable, request));
  }
}
