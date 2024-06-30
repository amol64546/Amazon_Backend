package com.bada.bazaar.responseDto;

import com.bada.bazaar.enums.CardType;
import com.bada.bazaar.util.FieldMasking;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class CardResponseDto implements Serializable {

    private String id;

    @JsonSerialize(using = FieldMasking.class)
    private String cardNo;
    private String expiry;
    private CardType cardType;

}
