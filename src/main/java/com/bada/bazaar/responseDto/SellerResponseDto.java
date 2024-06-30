package com.bada.bazaar.responseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class SellerResponseDto implements Serializable {

    private Integer id;

    private String name;

    private String username;

    private String phoneNumber;

    private String email;

    private Date dateJoined;
    private Date lastModifiedDate;

    private String address;
    private Integer age;
    private String gender;
}
