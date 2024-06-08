package com.BadaBazaar.BadaBazaar.controller;

import com.BadaBazaar.BadaBazaar.enums.ProductCategory;
import com.BadaBazaar.BadaBazaar.exception.SellerNotFoundException;
import com.BadaBazaar.BadaBazaar.requestDto.ProductByCategoryRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.ProductResponseDto;
import com.BadaBazaar.BadaBazaar.service.Imp.ProductServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductServiceImp productService;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductByCategoryRequestDto productByCategoryRequestDto) throws SellerNotFoundException {
        log.info("[POST]: Request to add product: {}", productByCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productByCategoryRequestDto));
    }

    @GetMapping("category/{productCategory}")
    public ResponseEntity<List<ProductResponseDto>> getProductByCategory(@PathVariable("productCategory") ProductCategory productCategory){
        log.info("[GET]: Request to get product by category: {}", productCategory);
        return ResponseEntity.ok().body(productService.getProductByCategory(productCategory));
    }
}
