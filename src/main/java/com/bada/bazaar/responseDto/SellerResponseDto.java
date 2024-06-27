package com.bada.bazaar.responseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class SellerResponseDto {

    private Integer sellerId;

    private String name;

    private String username;

    private String phoneNumber;

    private String email;

    private LocalDateTime dateJoined;

    private String address;
    private Integer age;
    private String gender;
}
