package com.bada.bazaar.requestDto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}

