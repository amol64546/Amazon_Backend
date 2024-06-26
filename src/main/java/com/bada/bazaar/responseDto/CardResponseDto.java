package com.bada.bazaar.responseDto;

import com.bada.bazaar.enums.CardType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class CardResponseDto {

    private String cardNo;
    private Integer cvv;
    private String expiry;
    private CardType cardType;

}
