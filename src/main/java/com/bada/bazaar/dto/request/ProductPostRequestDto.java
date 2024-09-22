package com.bada.bazaar.dto.request;

import com.bada.bazaar.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ProductPostRequestDto implements Serializable {

  @NotBlank(message = "Product name is required")
  private String name;
  private Category category;

  private String subCategory;

  @NotNull(message = "Seller id is required")
  private Integer sellerId;

  private String description;
  private Double price;
  private Integer stock;
  private byte[] image;

  private Map<String, String> characteristics;

  private List<String> tags = new ArrayList<>();
}
