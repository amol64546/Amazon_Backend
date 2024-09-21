package com.bada.bazaar.dto.request;

import com.bada.bazaar.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ProductPutRequestDto implements Serializable {

  private String name;
  private Category category;
  private Enum<?> subCategory;

  private String description;
  private Double price;
  private Integer stock;
  private byte[] image;

}

