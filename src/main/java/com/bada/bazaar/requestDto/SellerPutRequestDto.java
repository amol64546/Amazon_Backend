package com.bada.bazaar.requestDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SellerPutRequestDto implements Serializable {

  private String name;

  @Pattern(
    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
    message = "Invalid email address"
  )
  private String email;

  @Size(min = 4, max = 4, message = "Password must be 4 characters long")
  private String password;

  private String username;

  @Size(min = 10, max = 10, message = "Phone number must be 10 characters long")
  private String phoneNumber;

  private String address;
  private Integer age;
  private String gender;

}

