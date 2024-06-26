package com.bada.bazaar.requestDto;

import com.bada.bazaar.enums.ProductCategory;
import com.bada.bazaar.enums.ProductStatus;
import com.bada.bazaar.enums.ProductSubCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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

    private List<String> tags = new ArrayList<>();

}
