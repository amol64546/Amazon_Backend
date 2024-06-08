package com.BadaBazaar.BadaBazaar.model;

import com.BadaBazaar.BadaBazaar.enums.CardType;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String cardNo;

    private int cvv;
    private String expiry;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne
    @JoinColumn
    private Customer customer;
}
