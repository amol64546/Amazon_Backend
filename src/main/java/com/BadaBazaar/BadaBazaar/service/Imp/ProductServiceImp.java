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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public String addProduct(ProductByCategoryRequestDto productByCategoryRequestDto) throws SellerNotFoundException {
        Seller seller;
        try{
            seller = sellerRepository.findById(productByCategoryRequestDto.getSellerId()).get();
        }catch (Exception e){
            throw new SellerNotFoundException("Seller does not present");
        }

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
