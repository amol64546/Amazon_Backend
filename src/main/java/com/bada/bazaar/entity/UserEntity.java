package com.bada.bazaar.entity;

import com.bada.bazaar.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

  @Id
  private Integer userId;
  private String username;
  private String password;
  private Role role;

}

