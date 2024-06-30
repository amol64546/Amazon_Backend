package com.bada.bazaar.entity;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
  @Index(name = "customer_username_email_phoneNumber",
    columnList = "username, email, phoneNumber")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String address;
    private Integer age;
    private String gender;

    @Column(unique = true)
    private String username;
    private String password;

    private Boolean notification = true;

    private Integer cartId;

    @ElementCollection
    private List<Integer> cardIds = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dateJoined;

    @LastModifiedDate
    private Date lastModifiedDate;

    @ElementCollection
    private List<Integer> purchaseHistoryIds = new LinkedList<>();

    @ElementCollection
    private List<Integer> wishlistIds = new LinkedList<>();

    @ElementCollection
    private List<Integer> cartHistoryIds = new LinkedList<>();

    @ElementCollection
    private List<Integer> browsingHistoryIds = new LinkedList<>();

    @ElementCollection
    private List<String> searchHistory = new LinkedList<>();

}
