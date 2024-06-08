package com.BadaBazaar.BadaBazaar.requestDto;

import com.BadaBazaar.BadaBazaar.enums.ProductCategory;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductByCategoryRequestDto {

    private String name;
    private int price;
    private int quantity;

    private ProductCategory productCategory;

    private int sellerId;
}
