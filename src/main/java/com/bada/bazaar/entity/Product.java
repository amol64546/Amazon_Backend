package com.bada.bazaar.entity;

import com.bada.bazaar.enums.Category;
import com.bada.bazaar.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product implements Serializable {

  @Id
  @GeneratedValue
  private Integer id;

  private String name;
  private String description;
  private Double price;
  private Integer stock;
  private byte[] image;

  @ElementCollection
  private Map<String, String> characteristics = new LinkedHashMap<>();

  @Enumerated(EnumType.STRING)
  private Category category;

  private String subCategory;

  @Enumerated(EnumType.STRING)
  private ProductStatus productStatus = ProductStatus.AVAILABLE;

  @Column(updatable = false)
  private Date dateAdded;

  private Date lastModifiedDate;

  @ElementCollection
  private List<String> tags = new ArrayList<>();

  @ElementCollection
  private List<String> reviews = new ArrayList<>();

  private Integer rating = 0;
  private Integer views = 0;

  private Integer sellerId;
  private Integer itemId;


}
