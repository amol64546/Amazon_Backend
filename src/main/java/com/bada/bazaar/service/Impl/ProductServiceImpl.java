package com.bada.bazaar.service.Impl;

import com.bada.bazaar.dto.request.ProductPostRequestDto;
import com.bada.bazaar.dto.request.ProductPutRequestDto;
import com.bada.bazaar.dto.response.ProductResponseDto;
import com.bada.bazaar.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

  @Override
  public ProductResponseDto addProductBySellerId(ProductPostRequestDto productPostRequestDto,
    HttpServletRequest request) {
    return null;
  }

  @Override
  public ProductResponseDto updateProduct(Integer productId,
    ProductPutRequestDto productPutRequestDto, HttpServletRequest request) {
    return null;
  }

  @Override
  public ModelMap deleteProduct(Integer productId, HttpServletRequest request) {
    return null;
  }

  @Override
  public ProductResponseDto getProductById(Integer productId, HttpServletRequest request) {
    return null;
  }

  @Override
  public List<ProductResponseDto> getProductsBySellerId(Integer sellerId, Pageable pageable,
    HttpServletRequest request) {
    return List.of();
  }

  @Override
  public List<ProductResponseDto> getAllProducts(Pageable pageable, HttpServletRequest request) {
    return List.of();
  }
}