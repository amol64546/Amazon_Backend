package com.BadaBazaar.BadaBazaar.Controller;

import com.BadaBazaar.BadaBazaar.Enum.ProductCategory;
import com.BadaBazaar.BadaBazaar.Exception.SellerNotFoundException;
import com.BadaBazaar.BadaBazaar.RequestDto.ProductByCategoryRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.ProductResponseDto;
import com.BadaBazaar.BadaBazaar.Service.Imp.ProductServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
