package com.BadaBazaar.BadaBazaar.converter;

import com.BadaBazaar.BadaBazaar.enums.ProductStatus;
import com.BadaBazaar.BadaBazaar.model.Product;
import com.BadaBazaar.BadaBazaar.requestDto.ProductByCategoryRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.ItemResponseDto;
import com.BadaBazaar.BadaBazaar.responseDto.ProductResponseDto;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@Builder
@UtilityClass
public class ProductConverter {

    public static Product productRequestDtoToProduct(ProductByCategoryRequestDto productByCategoryRequestDto){
        return Product.builder()
                .name(productByCategoryRequestDto.getName())
                .price(productByCategoryRequestDto.getPrice())
                .quantity(productByCategoryRequestDto.getQuantity())
                .productCategory(productByCategoryRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();

    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .name(product.getName())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

    }

    public static ItemResponseDto productToItemResponseDto(Product product){
        return ItemResponseDto.builder()
                .productName(product.getName())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .build();
    }
}
