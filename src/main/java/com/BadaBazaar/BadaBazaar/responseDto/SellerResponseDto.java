package com.BadaBazaar.BadaBazaar.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerResponseDto {

    private int id;

    private String sellerName;

    private String mobNo;

    private String email;

    private String panNo;
}
