package com.bada.bazaar.requestDto;

import com.bada.bazaar.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPostRequestDto implements Serializable {

  @NotBlank(message = "Name cannot be empty")
  private String name;

  @NotBlank(message = "Username cannot be empty")
  private String username;

  @NotBlank(message = "Password cannot be empty")
  @Size(min = 4, max = 4, message = "Password must be 4 characters long")
  private String password;

  private Role role;

  @Pattern(
    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
    message = "Invalid email address"
  )
  @NotBlank(message = "Email cannot be empty")
  private String email;

  @Column(unique = true)
  @NotBlank(message = "Phone number cannot be empty")
  @Size(min = 10, max = 10, message = "Phone number must be 10 characters long")
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

