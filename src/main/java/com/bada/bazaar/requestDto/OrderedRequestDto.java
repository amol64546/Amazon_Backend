package com.bada.bazaar.requestDto;

import com.bada.bazaar.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderedRequestDto {

    private Integer productId;
    private Integer buyerId;
    private Integer quantity;
    private String shippingAddress;
    private CardType paymentMode;
}
