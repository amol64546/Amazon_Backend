package com.bada.bazaar.dto.request;

import com.bada.bazaar.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CardPostRequestDto implements Serializable {

    private String cardNo;
    private Integer cvv;
    private String expiry;
    private CardType cardType;
    private Integer buyerId;
}
