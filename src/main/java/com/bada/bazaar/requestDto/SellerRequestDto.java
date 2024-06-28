package com.bada.bazaar.requestDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerRequestDto {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Pattern(
      regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
      message = "Invalid email address"
    )
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 4, max = 4, message = "Password must be 4 characters long")
    private String password;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Phone number cannot be empty")
    @Size(min = 10, max = 10, message = "Phone number must be 10 characters long")
    private String phoneNumber;

    private String address;

    @Size(min = 1, max = 2, message = "Age must be between 1 and 2")
    private Integer age;
    private String gender;

}
