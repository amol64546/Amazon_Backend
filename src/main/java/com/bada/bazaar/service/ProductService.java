package com.bada.bazaar.service;

import com.bada.bazaar.dto.request.ProductPostRequestDto;
import com.bada.bazaar.dto.request.ProductPutRequestDto;
import com.bada.bazaar.dto.response.ProductResponseDto;
import com.bada.bazaar.entity.Product;
import com.bada.bazaar.enums.Category;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;

public interface ProductService {

  ProductResponseDto addProductBySellerId(ProductPostRequestDto productPostRequestDto, HttpServletRequest request);

  ProductResponseDto updateProduct(ProductPutRequestDto productPutRequestDto, HttpServletRequest request);

  ModelMap deleteProduct(Integer productId, HttpServletRequest request);

  Page<Product> getProductsBySellerId(Integer sellerId, Pageable pageable, HttpServletRequest request);

  Page<Product> getProductByCategory(Category category, Pageable pageable, HttpServletRequest request);
}
