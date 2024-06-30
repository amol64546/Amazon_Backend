package com.bada.bazaar.responseDto;

import com.bada.bazaar.enums.CardType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class OrderedResponseDto implements Serializable {

  private String id;
  private String productName;
  private Date orderDate;
  private Double itemPrice;
  private Integer quantity;
  private Double totalCost;
  private Double deliveryCharge;
  private CardType paymentMode;
  private String shippingAddress;

}
