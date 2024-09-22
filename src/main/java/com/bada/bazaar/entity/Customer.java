package com.bada.bazaar.entity;

import com.bada.bazaar.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer implements Serializable {

  @Id
  private Integer id;

  private Role role;
  private String name;

  @Column(unique = true)
  private String username;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String phoneNumber;

  private String shippingAddress;
  private Integer age;
  private String gender;

  @Column(updatable = false)
  private Date dateJoined;

  private Date lastModifiedDate;

  private Boolean notification = true;

  private Integer cartId;

  @ElementCollection
  private List<Integer> cardIds = new ArrayList<>();

  // productIds

  @ElementCollection
  private List<Integer> purchaseHistoryIds = new ArrayList<>();

  @ElementCollection
  private List<Integer> wishlistIds = new ArrayList<>();

  @ElementCollection
  private List<Integer> cartHistoryIds = new ArrayList<>();

  @ElementCollection
  private List<Integer> browsingHistoryIds = new ArrayList<>();

  @ElementCollection
  private List<String> searchHistoryIds = new ArrayList<>();

}
