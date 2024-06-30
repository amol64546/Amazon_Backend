package com.bada.bazaar.entity;

import com.bada.bazaar.model.UserInfo;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class Seller extends UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer rating;

    @ElementCollection
    private Map<Integer,Integer> salesHistory = new LinkedHashMap<>(); // productId -> quantity

    @ElementCollection
    private List<String> reviews = new LinkedList<>();

    @ElementCollection
    private List<Integer> productIds = new LinkedList<>();


}
