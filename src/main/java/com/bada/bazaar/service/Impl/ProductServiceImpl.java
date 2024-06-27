package com.bada.bazaar.service.Impl;

import com.BadaBazaar.BadaBazaar.converter.ProductConverter;
import com.bada.bazaar.enums.ProductCategory;
import com.bada.bazaar.repository.ProductRepository;
import com.bada.bazaar.requestDto.ProductRequestDto;
import com.bada.bazaar.model.Product;
import com.bada.bazaar.model.Seller;
import com.bada.bazaar.repository.SellerRepository;
import com.bada.bazaar.responseDto.ProductResponseDto;
import com.bada.bazaar.service.ProductService;
import com.bada.bazaar.util.SellerCacheUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final SellerRepository sellerRepository;

    private final ProductRepository productRepository;
    private final SellerCacheUtil sellerCacheUtil;

    @Override
    public String addProduct(ProductRequestDto productRequestDto) {
        Seller seller = sellerCacheUtil.getSeller(productRequestDto.getSellerId());

        Product product = ProductConverter.productRequestDtoToProduct(productRequestDto);

        product.setSeller(seller);
        seller.getProductList().add(product);

        sellerRepository.save(seller);
        return "Product has been added";
    }

    @Override
    public List<ProductResponseDto> getProductByCategory(ProductCategory productCategory) {
        List<Product> productList = productRepository.findAllByProductCategory(productCategory);

        List<ProductResponseDto> productByCategoryResponseDtoList = new ArrayList<>();
        for(Product product: productList){
            productByCategoryResponseDtoList.add(ProductConverter.productToProductResponseDto(product));
        }
        return productByCategoryResponseDtoList;

    }
}
