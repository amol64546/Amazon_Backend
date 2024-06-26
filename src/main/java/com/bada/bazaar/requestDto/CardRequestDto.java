package com.bada.bazaar.requestDto;

import com.bada.bazaar.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardRequestDto {

    private String cardNo;
    private Integer cvv;
    private String expiry;
    private CardType cardType;
    private Integer buyerId;
}
