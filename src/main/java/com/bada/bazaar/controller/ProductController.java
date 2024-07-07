package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.ProductPostRequestDto;
import com.bada.bazaar.dto.request.ProductPutRequestDto;
import com.bada.bazaar.dto.response.ProductResponseDto;
import com.bada.bazaar.enums.Category;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Product")
@RequestMapping("/v1/products")
public interface ProductController {

  //       ROLE - SELLER
  @PostMapping
  ResponseEntity<ProductResponseDto> addProductBySellerId(
    @Validated @Valid @RequestBody ProductPostRequestDto productPostRequestDto,
    HttpServletRequest request);

  @PutMapping("{productId}")
  ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Integer productId,
    @Validated @Valid @RequestBody ProductPutRequestDto productPutRequestDto,
    HttpServletRequest request);

  @DeleteMapping("/{productId}")
  ResponseEntity<ModelMap> deleteProduct(@PathVariable Integer productId,
    HttpServletRequest request);

  @GetMapping("/{sellerId}")
  ResponseEntity<List<ProductResponseDto>> getProductsBySellerId(
    @PathVariable Integer sellerId,
    @Parameter(hidden = true) @PageableDefault(sort = "dateAdded",
      direction = Sort.Direction.DESC) Pageable pageable,
    HttpServletRequest request);


  @GetMapping("category/{category}")
  ResponseEntity<List<ProductResponseDto>> getProductByCategory(@PathVariable Category category);

}
