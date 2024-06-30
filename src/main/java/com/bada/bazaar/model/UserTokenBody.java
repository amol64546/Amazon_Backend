package com.bada.bazaar.model;

import com.bada.bazaar.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTokenBody {

  private Integer userId;
  private String username;
  private Role role;

}

