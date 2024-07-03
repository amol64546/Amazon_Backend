package com.bada.bazaar.responseDto;

import com.bada.bazaar.util.FieldMasking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SellerResponseDto implements Serializable {

    private Integer id;

    private String name;

    private String username;

    @JsonSerialize(using = FieldMasking.class)
    private String phoneNumber;

    private String email;

    private Date dateJoined;
    private Date lastModifiedDate;

    private String address;
    private Integer age;
    private String gender;
}
