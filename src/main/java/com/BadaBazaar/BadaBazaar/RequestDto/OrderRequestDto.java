package com.BadaBazaar.BadaBazaar.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private int productId;
    private int customerId;
    private int requiredQuantity;
}
