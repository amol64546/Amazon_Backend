package com.bada.bazaar.responseDto;

import com.bada.bazaar.enums.Role;
import com.bada.bazaar.util.FieldMasking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class UserResponseDto implements Serializable {

  private String name;

  private String username;

  private Role role;

  private String email;

  @JsonSerialize(using = FieldMasking.class)
  private String phoneNumber;

  private String address;
  private Integer age;
  private String gender;

  @CreatedDate
  @Column(updatable = false)
  @JsonIgnore
  private Date dateJoined;

  @LastModifiedDate
  @JsonIgnore
  private Date lastModifiedDate;

}

