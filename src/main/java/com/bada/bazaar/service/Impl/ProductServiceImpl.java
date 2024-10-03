package com.bada.bazaar.service.Impl;

import com.bada.bazaar.converter.ProductConverter;
import com.bada.bazaar.dto.request.ProductPostRequestDto;
import com.bada.bazaar.dto.request.ProductPutRequestDto;
import com.bada.bazaar.dto.response.ProductResponseDto;
import com.bada.bazaar.entity.Product;
import com.bada.bazaar.enums.Category;
import com.bada.bazaar.error.ApiException;
import com.bada.bazaar.error.ErrorConstants;
import com.bada.bazaar.repository.ProductRepository;
import com.bada.bazaar.repository.SellerRepository;
import com.bada.bazaar.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductConverter productConverter;
  private final SellerRepository sellerRepository;

  @Override
  public ProductResponseDto addProductBySellerId(ProductPostRequestDto productPostRequestDto,
                                                 HttpServletRequest request) {
    if (!sellerRepository.existsById(productPostRequestDto.getSellerId())) {
      throw new ApiException(ErrorConstants.SELLER_NOT_FOUND);
    }
    Product product = productConverter.productPostRequestDtoToProduct(productPostRequestDto);
    productRepository.save(product);
    return productConverter.productToProductResponseDto(product);
  }

  @Override
  public ProductResponseDto updateProduct(ProductPutRequestDto productPutRequestDto,
                                          HttpServletRequest request) {
    if (!productRepository.existsById(productPutRequestDto.getId())) {
      throw new ApiException(ErrorConstants.PRODUCT_NOT_FOUND);
    }

    Product product = productConverter.productPutRequestDtoToProduct(productPutRequestDto);
    productRepository.save(product);
    return productConverter.productToProductResponseDto(product);
  }

  @Override
  public ModelMap deleteProduct(Integer productId, HttpServletRequest request) {

    if (!productRepository.existsById(productId)) {
      throw new ApiException(ErrorConstants.PRODUCT_NOT_FOUND);
    }

    productRepository.deleteById(productId);
    return new ModelMap()
      .addAttribute("msg", "Product deleted successfully");
  }


  @Override
  public Page<Product> getProductsBySellerId(Integer sellerId, Pageable pageable,
                                             HttpServletRequest request) {
    if(!sellerRepository.existsById(sellerId)) {
      throw new ApiException(ErrorConstants.SELLER_NOT_FOUND);
    }
    Product probe = Product.builder()
      .sellerId(sellerId)
      .build();
    Example<Product> example = Example.of(probe);
    return productRepository.findAll(example, pageable);
  }

  @Override
  public Page<Product> getProductByCategory(Category category, Pageable pageable, HttpServletRequest request) {
    if (category == null) {
      return Page.empty(pageable);
    }

    Product probe = Product.builder()
      .category(category)
      .build();
    Example<Product> example = Example.of(probe);
    return productRepository.findAll(example, pageable);
  }

}