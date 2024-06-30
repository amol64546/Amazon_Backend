package com.bada.bazaar.entity;

import com.bada.bazaar.enums.Category;
import com.bada.bazaar.enums.ProductStatus;
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
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  private LocalDateTime dateAdded;

  @ElementCollection
  private List<String> tags = new LinkedList<>();

  @ElementCollection
  private List<String> reviews = new LinkedList<>();

  private Integer rating;
  private Integer views;

  private Integer sellerId;
  private Integer itemId;


}
