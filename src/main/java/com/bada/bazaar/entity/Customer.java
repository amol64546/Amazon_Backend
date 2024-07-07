package com.bada.bazaar.entity;

import com.bada.bazaar.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {
  @Index(name = "customer_username_email_phoneNumber",
    columnList = "username, email, phoneNumber")
})
public class Customer implements Serializable {

  @Id
  @GeneratedValue
  private Integer id;

  private Role role;
  private String name;

  @Column(unique = true)
  private String username;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String phoneNumber;

  private String address;
  private Integer age;
  private String gender;

  @Column(updatable = false)
  private Date dateJoined;

  private Date lastModifiedDate;

  private Boolean notification = true;

  private Integer cartId;

  @ElementCollection
  private List<Integer> cardIds = new ArrayList<>();

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
