package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.ProductController;
import com.bada.bazaar.dto.request.ProductPostRequestDto;
import com.bada.bazaar.dto.request.ProductPutRequestDto;
import com.bada.bazaar.dto.response.ProductResponseDto;
import com.bada.bazaar.entity.Product;
import com.bada.bazaar.enums.Category;
import com.bada.bazaar.service.ProductService;
import com.bada.bazaar.util.CommonServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductControllerImpl implements ProductController {

  private final ProductService productService;
  private final CommonServices commonServices;

  @Override
  public ResponseEntity<ProductResponseDto> addProductBySellerId(
    ProductPostRequestDto productPostRequestDto,
    HttpServletRequest request) {
    log.info("[POST]: Request to add product: {}",
      commonServices.prettyPrint(productPostRequestDto));
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(productService.addProductBySellerId(productPostRequestDto, request));
  }

  @Override
  public ResponseEntity<ProductResponseDto> updateProduct(
    ProductPutRequestDto productPutRequestDto,
    HttpServletRequest request) {
    log.info("[PUT]: Request to update product by ID: {}", productPutRequestDto.getId());
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.updateProduct(productPutRequestDto, request));
  }

  @Override
  public ResponseEntity<ModelMap> deleteProduct(Integer productId, HttpServletRequest request) {
    log.info("[DELETE]: Request to delete product by ID: {}", productId);
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.deleteProduct(productId, request));
  }

  @Override
  public ResponseEntity<Page<Product>> getProductsBySellerId(
    Integer sellerId,
    Pageable pageable, HttpServletRequest request) {
    log.info("[GET]: Request to get products by seller ID: {}", sellerId);
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.getProductsBySellerId(sellerId, pageable, request));
  }

  @Override
  public ResponseEntity<Page<Product>> getProductByCategory(
    Category category,
    Pageable pageable, HttpServletRequest request) {
    log.info("[GET]: Request to get products by category: {}", category);
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.getProductByCategory(category,pageable,request));
  }
}
