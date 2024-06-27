package com.bada.bazaar.requestDto;

import com.bada.bazaar.enums.ProductCategory;
import com.bada.bazaar.enums.ProductSubCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestDto {

    @NotBlank(message = "Product name is required")
    private String name;

    private int quantity;

    private ProductCategory productCategory;
    private ProductSubCategory productSubCategory;

    @NotBlank(message = "Seller id is required")
    private int sellerId;

    private String description;
    private Double price;
    private Integer stock;

    private byte[] image;
    private String brand;
    private String color;
    private String size;
    private String material;

    private List<String> tags = new LinkedList<>();

}
