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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class Customer extends UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

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
