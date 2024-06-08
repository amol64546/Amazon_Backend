package com.BadaBazaar.BadaBazaar.model;

import java.io.Serializable;
import lombok.*;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "seller")
public class Seller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

//    @Column(unique = true)
    private String mobNo;

//    @Column(unique = true)
    private String email;

    private int age;

//    @Column(unique = true)
    private String panNo;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();


}
