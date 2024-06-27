package com.bada.bazaar.model;

import com.bada.bazaar.enums.CardType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Ordered implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime orderDate;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime deliveryDate;

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
