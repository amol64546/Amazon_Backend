package com.bada.bazaar.responseDto;

import com.bada.bazaar.util.PhoneNumberSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class CustomerResponseDto implements Serializable {

    private Integer id;
    private String name;
    private String email;
    @JsonSerialize(using = PhoneNumberSerializer.class)
    private String phoneNumber;
    private String address;
    private Integer age;
    private String gender;
    private String username;
    private Boolean notification;
    private LocalDateTime dateJoined;
    private Date lastModifiedDate;
}
