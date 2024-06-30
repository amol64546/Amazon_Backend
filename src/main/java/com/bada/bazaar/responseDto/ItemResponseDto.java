package com.bada.bazaar.responseDto;

import com.bada.bazaar.enums.Category;
import com.bada.bazaar.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class ItemResponseDto implements Serializable {

  private Integer id;
  private String productName;
  private Integer quantity;
  private Double price;
  private Category category;
  private ProductStatus productStatus;
  private Date dateAdded;
}
