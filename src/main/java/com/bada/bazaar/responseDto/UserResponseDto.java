package com.bada.bazaar.responseDto;

import com.bada.bazaar.enums.Role;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UserResponseDto implements Serializable {

  private Integer id;
  private String name;

  private String username;

  private Role role;

  private String email;

  @JsonSerialize(using = FieldMasking.class)
  private String phoneNumber;

  private String address;
  private Integer age;
  private String gender;

  private Date dateJoined;

  private Date lastModifiedDate;

}

