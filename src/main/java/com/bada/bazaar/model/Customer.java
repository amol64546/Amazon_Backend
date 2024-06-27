package com.bada.bazaar.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Integer age;
    private String gender;
    private String username;
    private String password;

    private Boolean notification = true;

    private Integer cartId;

    @ElementCollection
    private List<Integer> cardIds = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dateJoined;

    @ElementCollection
    private List<Integer> purchaseHistoryIds = new ArrayList<>();

    @ElementCollection
    private List<Integer> wishlistIds = new ArrayList<>();

    @ElementCollection
    private List<Integer> cartHistoryIds = new ArrayList<>();

    @ElementCollection
    private List<Integer> browsingHistoryIds = new ArrayList<>();

    @ElementCollection
    private List<String> searchHistory = new ArrayList<>();

}
