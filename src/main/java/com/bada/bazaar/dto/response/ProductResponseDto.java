package com.bada.bazaar.dto.response;

import com.bada.bazaar.enums.Category;
import com.bada.bazaar.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProductResponseDto implements Serializable {

  private Integer id;

  private String name;

  private Category category;
  private Enum<?> subCategory;

  private ProductStatus productStatus;

  private String description;
  private Double price;
  private Integer stock;
  private byte[] image;

  private Map<String, String> characteristics;

  private Date dateAdded;
  private Date lastModifiedDate;

  private List<String> tags;

  private Integer sellerId;

}
