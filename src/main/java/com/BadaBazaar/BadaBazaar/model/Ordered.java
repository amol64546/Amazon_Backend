package com.BadaBazaar.BadaBazaar.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordered")
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date orderDate;

    private int totalCost;

    private int deliveryCharge;

    private String cardUsedForPayment;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "ordered",cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();

}
