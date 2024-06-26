package com.bada.bazaar.service;

import com.bada.bazaar.enums.ProductCategory;
import com.bada.bazaar.exception.SellerNotFoundException;
import com.bada.bazaar.requestDto.ProductRequestDto;
import com.bada.bazaar.responseDto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    String addProduct(
      ProductRequestDto productRequestDto) throws SellerNotFoundException;

    List<ProductResponseDto> getProductByCategory(ProductCategory productCategory);
}
