package com.bada.bazaar.responseDto;

import com.bada.bazaar.enums.CardType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class OrderedResponseDto {

    private String productName;
    private LocalDateTime orderDate;
    private Double itemPrice;
    private Integer quantity;
    private Double totalCost;
    private Double deliveryCharge;
    private CardType paymentMode;
    private String shippingAddress;

}
