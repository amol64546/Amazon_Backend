package com.BadaBazaar.BadaBazaar.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int cartTotal;

    @OneToOne
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();
}
