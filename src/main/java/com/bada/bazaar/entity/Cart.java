package com.bada.bazaar.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

  @Id
  @GeneratedValue
  private Integer id;

  private Integer totalAmount;

  private Integer buyerId;

  private Date dateAdded;

  @ElementCollection
  private List<Integer> itemIds = new LinkedList<>();

}
