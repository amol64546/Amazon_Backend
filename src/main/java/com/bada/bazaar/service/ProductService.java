package com.bada.bazaar.service;

import com.bada.bazaar.dto.request.ProductPostRequestDto;
import com.bada.bazaar.dto.request.ProductPutRequestDto;
import com.bada.bazaar.dto.response.ProductResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;

public interface ProductService {

  ProductResponseDto addProductBySellerId(ProductPostRequestDto productPostRequestDto, HttpServletRequest request);

  ProductResponseDto updateProduct(Integer productId, ProductPutRequestDto productPutRequestDto, HttpServletRequest request);

  ModelMap deleteProduct(Integer productId, HttpServletRequest request);

  ProductResponseDto getProductById(Integer productId, HttpServletRequest request);

  List<ProductResponseDto> getProductsBySellerId(Integer sellerId, Pageable pageable, HttpServletRequest request);

  List<ProductResponseDto> getAllProducts(Pageable pageable, HttpServletRequest request);
}
