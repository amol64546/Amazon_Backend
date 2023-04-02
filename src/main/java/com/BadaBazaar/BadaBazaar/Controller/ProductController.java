package com.BadaBazaar.BadaBazaar.Controller;

import com.BadaBazaar.BadaBazaar.Enum.ProductCategory;
import com.BadaBazaar.BadaBazaar.Exception.SellerNotFoundException;
import com.BadaBazaar.BadaBazaar.RequestDto.ProductByCategoryRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.ProductByCategoryResponseDto;
import com.BadaBazaar.BadaBazaar.Service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImp productService;

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductByCategoryRequestDto productByCategoryRequestDto) throws SellerNotFoundException {
        return productService.addProduct(productByCategoryRequestDto);

    }

    @GetMapping("/get/category/{productCategory}")
    public List<ProductByCategoryResponseDto> getProductByCategory(@PathVariable("productCategory") ProductCategory productCategory){
        return productService.getProductByCategory(productCategory);
    }
}
