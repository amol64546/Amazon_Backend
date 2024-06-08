package com.BadaBazaar.BadaBazaar.service.Imp;

import com.BadaBazaar.BadaBazaar.converter.ProductConverter;
import com.BadaBazaar.BadaBazaar.enums.ProductCategory;
import com.BadaBazaar.BadaBazaar.exception.SellerNotFoundException;
import com.BadaBazaar.BadaBazaar.repository.ProductRepository;
import com.BadaBazaar.BadaBazaar.requestDto.ProductByCategoryRequestDto;
import com.BadaBazaar.BadaBazaar.model.Product;
import com.BadaBazaar.BadaBazaar.model.Seller;
import com.BadaBazaar.BadaBazaar.repository.SellerRepository;
import com.BadaBazaar.BadaBazaar.responseDto.ProductResponseDto;
import com.BadaBazaar.BadaBazaar.service.ProductService;
import com.BadaBazaar.BadaBazaar.util.SellerCacheUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final SellerRepository sellerRepository;

    private final ProductRepository productRepository;
    private final SellerCacheUtil sellerCacheUtil;

    @Override
    public String addProduct(ProductByCategoryRequestDto productByCategoryRequestDto) {
        Seller seller = sellerCacheUtil.getSeller(productByCategoryRequestDto.getSellerId());

        Product product = ProductConverter.productRequestDtoToProduct(productByCategoryRequestDto);

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
