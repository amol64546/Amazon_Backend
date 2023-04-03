package com.BadaBazaar.BadaBazaar.Converter;

import com.BadaBazaar.BadaBazaar.Enum.ProductStatus;
import com.BadaBazaar.BadaBazaar.Model.Item;
import com.BadaBazaar.BadaBazaar.Model.Product;
import com.BadaBazaar.BadaBazaar.RequestDto.ProductByCategoryRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.ItemResponseDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.ProductResponseDto;
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
