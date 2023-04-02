package com.BadaBazaar.BadaBazaar.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

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
