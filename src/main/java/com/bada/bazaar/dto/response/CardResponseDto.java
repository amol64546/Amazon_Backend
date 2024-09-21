package com.bada.bazaar.dto.response;

import com.bada.bazaar.enums.CardType;
import com.bada.bazaar.util.FieldMasking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class CardResponseDto implements Serializable {

    private String id;

    @JsonSerialize(using = FieldMasking.class)
    private String cardNo;
    private String expiry;
    private CardType cardType;

}
