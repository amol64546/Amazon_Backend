package com.bada.bazaar.entity;

import com.bada.bazaar.enums.CardType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
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



