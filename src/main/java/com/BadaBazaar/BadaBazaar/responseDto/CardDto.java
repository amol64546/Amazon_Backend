package com.BadaBazaar.BadaBazaar.responseDto;

import com.BadaBazaar.BadaBazaar.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {

    private String cardNo;
    private int cvv;
    private String expiry;
    private CardType cardType;

}
