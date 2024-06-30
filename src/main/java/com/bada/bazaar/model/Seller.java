package com.bada.bazaar.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {
  @Index(name = "seller_username_email_phoneNumber",
    columnList = "username, email, phoneNumber")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String phoneNumber;

    private String password;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dateJoined;

    private String address;
    private Integer age;
    private String gender;

    private Integer rating;

    @ElementCollection
    private Map<Integer,Integer> salesHistory = new LinkedHashMap<>(); // productId -> quantity

    @ElementCollection
    private List<String> reviews = new LinkedList<>();

    @ElementCollection
    private List<Integer> productIds = new LinkedList<>();


}
