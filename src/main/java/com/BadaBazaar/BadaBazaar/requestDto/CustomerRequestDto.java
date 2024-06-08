package com.BadaBazaar.BadaBazaar.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    private String name;

    private String mobNo;

    private String email;

    private int age;
}
