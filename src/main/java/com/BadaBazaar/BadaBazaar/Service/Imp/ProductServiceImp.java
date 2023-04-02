package com.BadaBazaar.BadaBazaar.Service.Imp;

import com.BadaBazaar.BadaBazaar.Converter.ProductConverter;
import com.BadaBazaar.BadaBazaar.Enum.ProductCategory;
import com.BadaBazaar.BadaBazaar.Exception.SellerNotFoundException;
import com.BadaBazaar.BadaBazaar.Repository.ProductRepository;
import com.BadaBazaar.BadaBazaar.RequestDto.ProductByCategoryRequestDto;
import com.BadaBazaar.BadaBazaar.Model.Product;
import com.BadaBazaar.BadaBazaar.Model.Seller;
import com.BadaBazaar.BadaBazaar.Repository.SellerRepository;
import com.BadaBazaar.BadaBazaar.ResponseDto.ProductByCategoryResponseDto;
import com.BadaBazaar.BadaBazaar.Service.ProductService;
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
    public List<ProductByCategoryResponseDto> getProductByCategory(ProductCategory productCategory) {
        List<Product> productList = productRepository.findAllByProductCategory(productCategory);

        List<ProductByCategoryResponseDto> productByCategoryResponseDtoList = new ArrayList<>();
        for(Product product: productList){
            productByCategoryResponseDtoList.add(ProductConverter.productToProductResponseDto(product));
        }
        return productByCategoryResponseDtoList;

    }
}
