package com.bada.bazaar.responseDto;

import com.bada.bazaar.enums.ProductCategory;
import com.bada.bazaar.enums.ProductStatus;
import com.bada.bazaar.enums.ProductSubCategory;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private Integer id;

    private String name;

    private ProductCategory productCategory;

    private ProductSubCategory productSubCategory;

    private ProductStatus productStatus;

    private String description;
    private Double price;
    private Integer stock;

    private byte[] image;
    private String brand;
    private String color;
    private String size;
    private String material;

    private LocalDateTime dateAdded;

    private List<String> tags;

    private Integer sellerId;

}
