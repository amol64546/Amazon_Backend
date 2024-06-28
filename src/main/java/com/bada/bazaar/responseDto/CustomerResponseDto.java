package com.bada.bazaar.responseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class CustomerResponseDto {

    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Integer age;
    private String gender;
    private String username;
    private Boolean notification;
    private LocalDateTime dateJoined;
}
