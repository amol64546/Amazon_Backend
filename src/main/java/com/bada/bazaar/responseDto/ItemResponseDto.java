package com.bada.bazaar.responseDto;

import com.bada.bazaar.enums.Category;
import com.bada.bazaar.enums.ProductStatus;
import com.bada.bazaar.service.Impl.ProductServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class ItemResponseDto {

    private Integer id;
    private String productName;
    private Integer quantity;
    private Double price;
    private Category category;
    private ProductServiceImpl productServiceImpl;
    private ProductStatus productStatus;
    private LocalDateTime dateAdded;
}
