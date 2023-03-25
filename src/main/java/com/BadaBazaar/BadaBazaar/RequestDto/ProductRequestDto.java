package com.BadaBazaar.BadaBazaar.RequestDto;

import com.BadaBazaar.BadaBazaar.Enum.ProductCategory;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private String name;
    private int price;
    private int quantity;

    private ProductCategory productCategory;

    private int sellerId;
}
