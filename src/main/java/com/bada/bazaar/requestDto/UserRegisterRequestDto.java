package com.bada.bazaar.requestDto;

import com.bada.bazaar.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UserRegisterRequestDto implements Serializable {

  @NotBlank(message = "First Name cannot be empty")
  private String firstName;
  @NotBlank(message = "First Name cannot be empty")
  private String lastName;

  @NotBlank(message = "Username cannot be empty")
  private String username;

  @NotBlank(message = "Password cannot be empty")
  @Size(min = 4, max = 4, message = "Password must be 4 characters long")
  private String password;

  @NotNull(message = "Role cannot be empty")
  private Role role;

  @Pattern(
    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
    message = "Invalid email address"
  )
  @NotBlank(message = "Email cannot be empty")
  private String email;

  @Size(min = 10, max = 10, message = "Phone number must be 10 characters long")
  private String phoneNumber;

  private String address;
  private Integer age;
  private String gender;


}

