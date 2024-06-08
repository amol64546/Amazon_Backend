package com.BadaBazaar.BadaBazaar.requestDto;

import com.BadaBazaar.BadaBazaar.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {

    private String cardNo;
    private int cvv;
    private String expiry;

    private CardType cardType;
    private int customerId;
}
