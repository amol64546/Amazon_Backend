package com.bada.bazaar.dto.request;

import com.bada.bazaar.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ProductPutRequestDto implements Serializable {

  @NotNull(message = "ProductId is required")
  private Integer id;
  private String name;
  private Category category;
  private String subCategory;

  private String description;
  private Double price;
  private Integer stock;
  private byte[] image;

}

