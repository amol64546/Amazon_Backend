package com.BadaBazaar.BadaBazaar.Model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String mobNo;

    @Column(unique = true)
    private String email;

    private int age;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Card> cardList = new ArrayList<>();

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Ordered> orderList = new ArrayList<>();

}
