package com.bada.bazaar.requestDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequestDto {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Phone number cannot be empty")
    @Size(min = 10, max = 10, message = "Phone number must be 10 characters long")
    private String phoneNumber;

    @Pattern(
      regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
      message = "Invalid email address"
    )
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Username cannot be empty")
    private String username;
    
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 4, max = 4, message = "Password must be 4 characters long")
    private String password;

    private String address;
    private Integer age;
    private String gender;
}
