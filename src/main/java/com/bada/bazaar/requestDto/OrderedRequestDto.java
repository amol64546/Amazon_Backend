package com.bada.bazaar.requestDto;

import com.bada.bazaar.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderedRequestDto implements Serializable {

    private Integer productId;
    private Integer buyerId;
    private Integer quantity;
    private String shippingAddress;
    private CardType paymentMode;
}
