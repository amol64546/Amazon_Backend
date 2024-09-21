package com.bada.bazaar.entity;

import com.bada.bazaar.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Card implements Serializable {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(unique = true)
  private String cardNo;

  private Integer cvv;

  private String expiry;

  @Enumerated(EnumType.STRING)
  private CardType cardType;

  private Integer buyerId;

}



