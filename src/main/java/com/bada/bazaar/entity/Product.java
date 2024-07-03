package com.bada.bazaar.entity;

import com.bada.bazaar.enums.Category;
import com.bada.bazaar.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

  private Enum<?> subCategory;

  @Enumerated(EnumType.STRING)
  private ProductStatus productStatus = ProductStatus.AVAILABLE;

  @CreatedDate
  @Column(updatable = false)
  private Date dateAdded;

  @LastModifiedDate
  private Date lastModifiedDate;

  @ElementCollection
  private List<String> tags = new LinkedList<>();

  @ElementCollection
  private List<String> reviews = new LinkedList<>();

  private Integer rating;
  private Integer views;

  private Integer sellerId;
  private Integer itemId;


}
