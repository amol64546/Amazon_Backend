package com.BadaBazaar.BadaBazaar.Model;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int requiredQuantity;

    @ManyToOne
    @JoinColumn
    private Cart cart;

    @OneToOne
    @JoinColumn
    private Product product;

    @ManyToOne
    @JoinColumn
    private Ordered ordered;

}
