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

    private String name;

    private String mobNo;

    private String email;

    private int age;

    private String panNo;
}
