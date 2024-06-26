package com.bada.bazaar.model;

import com.bada.bazaar.enums.ProductCategory;
import com.bada.bazaar.enums.ProductStatus;
import com.bada.bazaar.enums.ProductSubCategory;
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
import java.util.ArrayList;
import java.util.List;
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

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    private ProductSubCategory productSubCategory;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.AVAILABLE;

    private String description;
    private Double price;
    private Integer stock;

    private byte[] image;
    private String brand;
    private String color;
    private String size;
    private String material;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dateAdded;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @ElementCollection
    private List<String> reviews = new ArrayList<>();
    private Integer rating;
    private Integer views;

    private Integer sellerId;
    private Integer itemId;


}
