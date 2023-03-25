package com.BadaBazaar.BadaBazaar.RequestDto;

import com.BadaBazaar.BadaBazaar.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
