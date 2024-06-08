package com.BadaBazaar.BadaBazaar.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private String productName;
    private Date orderDate;
    private int itemPrice;
    private int quantityOrdered;
    private int totalCost;
    private int deliveryCharge;
    private String cardUsedForPayment;

}
