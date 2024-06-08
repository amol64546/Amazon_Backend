package com.BadaBazaar.BadaBazaar.service;

import com.BadaBazaar.BadaBazaar.enums.ProductCategory;
import com.BadaBazaar.BadaBazaar.exception.SellerNotFoundException;
import com.BadaBazaar.BadaBazaar.requestDto.ProductByCategoryRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    public String addProduct(ProductByCategoryRequestDto productByCategoryRequestDto) throws SellerNotFoundException;

    public List<ProductResponseDto> getProductByCategory(ProductCategory productCategory);
}
