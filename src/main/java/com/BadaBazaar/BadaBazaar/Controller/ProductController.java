package com.BadaBazaar.BadaBazaar.Controller;

import com.BadaBazaar.BadaBazaar.Enum.ProductCategory;
import com.BadaBazaar.BadaBazaar.Exception.SellerNotFoundException;
import com.BadaBazaar.BadaBazaar.RequestDto.ProductRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.ProductResponseDto;
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
    public String addProduct(@RequestBody ProductRequestDto productRequestDto) throws SellerNotFoundException {
        return productService.addProduct(productRequestDto);

    }

    @GetMapping("/get/category/{productCategory}")
    public List<ProductResponseDto> getProductByCategory(@PathVariable("productCategory") ProductCategory productCategory){
        return productService.getProductByCategory(productCategory);
    }
}
