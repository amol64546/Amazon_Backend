package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.enums.ProductCategory;
import com.bada.bazaar.requestDto.ProductRequestDto;
import com.bada.bazaar.responseDto.ProductResponseDto;
import com.bada.bazaar.service.Impl.ProductServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductControllerImpl {

    private final ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductRequestDto productRequestDto) {
        log.info("[POST]: Request to add product: {}", productRequestDto);

        String response;
        try {
            response = productService.addProduct(productRequestDto);
        } catch (SellerNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("category/{productCategory}")
    public ResponseEntity<List<ProductResponseDto>> getProductByCategory(@PathVariable("productCategory") ProductCategory productCategory){
        log.info("[GET]: Request to get product by category: {}", productCategory);
        return ResponseEntity.ok().body(productService.getProductByCategory(productCategory));
    }
}
