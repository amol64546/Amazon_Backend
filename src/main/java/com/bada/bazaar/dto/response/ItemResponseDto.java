package com.bada.bazaar.dto.response;

import com.bada.bazaar.enums.Category;
import com.bada.bazaar.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ItemResponseDto implements Serializable {

  private Integer id;
  private String productName;
  private Integer quantity;
  private Double price;
  private Category category;
  private ProductStatus productStatus;
  private Date dateAdded;
}
