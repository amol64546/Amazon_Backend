package com.bada.bazaar.requestDto;

import com.bada.bazaar.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPutRequestDto implements Serializable {

  private String name;
  private Category category;
  private Enum<?> subCategory;

  private String description;
  private Double price;
  private Integer stock;
  private byte[] image;

}

