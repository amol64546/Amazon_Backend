package com.bada.bazaar.entity;

import com.bada.bazaar.enums.Role;
import com.bada.bazaar.util.AttributeEncryptor;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
public class UserInfo implements Serializable {

  private Role role;
  private String name;

  @Column(unique = true)
  private String username;

  @Convert(converter = AttributeEncryptor.class)
  private String password;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String phoneNumber;

  private String address;
  private Integer age;
  private String gender;

  @CreatedDate
  @Column(updatable = false)
  private Date dateJoined;

  @LastModifiedDate
  private Date lastModifiedDate;



}

