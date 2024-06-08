package com.BadaBazaar.BadaBazaar.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerRequestDto {

    @NotBlank
    private String sellerName;

    @NotBlank
    @Size(min = 10, max = 10)
    private String mobNo;

    private String email;

    private int age;

    @NotBlank
    private String panNo;
}
