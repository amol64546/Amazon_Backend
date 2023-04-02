package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.Enum.ProductCategory;
import com.BadaBazaar.BadaBazaar.Exception.SellerNotFoundException;
import com.BadaBazaar.BadaBazaar.RequestDto.ProductByCategoryRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.ProductByCategoryResponseDto;

import java.util.List;

public interface ProductService {

    public String addProduct(ProductByCategoryRequestDto productByCategoryRequestDto) throws SellerNotFoundException;

    public List<ProductByCategoryResponseDto> getProductByCategory(ProductCategory productCategory);
}
