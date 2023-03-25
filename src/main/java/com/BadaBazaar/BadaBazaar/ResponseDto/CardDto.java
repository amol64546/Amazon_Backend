package com.BadaBazaar.BadaBazaar.ResponseDto;

import com.BadaBazaar.BadaBazaar.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
