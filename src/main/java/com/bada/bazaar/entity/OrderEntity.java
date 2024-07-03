package com.bada.bazaar.entity;

import com.bada.bazaar.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity implements Serializable {

  @Id
  @GeneratedValue
  private Integer id;

  @CreatedDate
  @Column(updatable = false)
  private Date orderDate;

  @CreatedDate
  @Column(updatable = false)
  private Date deliveryDate;

  private Double totalCost;

  private Double deliveryCharge;

  private String shippingAddress;

  private CardType paymentMode;

  private String paymentStatus;

  private Integer buyerId;

  @ElementCollection
  private List<Integer> itemIds = new LinkedList<>();

  private Integer quantity;


}
