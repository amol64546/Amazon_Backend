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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Seller implements Serializable {

  @Id
  @GeneratedValue
  private Integer id;

  private Integer rating;

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

  @LastModifiedDate
  private Date lastModifiedDate;

  @ElementCollection
  private Map<Integer, Integer> salesHistory = new LinkedHashMap<>(); // productId -> quantity

  @ElementCollection
  private List<String> reviews = new LinkedList<>();

  @ElementCollection
  private List<Integer> productIds = new LinkedList<>();


}

