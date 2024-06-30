package com.bada.bazaar.service.Impl;

import com.bada.bazaar.entity.Admin;
import com.bada.bazaar.entity.Customer;
import com.bada.bazaar.entity.Seller;
import com.bada.bazaar.repository.AdminRepository;
import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.repository.SellerRepository;
import com.bada.bazaar.requestDto.LoginRequest;
import com.bada.bazaar.requestDto.UserPostRequestDto;
import com.bada.bazaar.responseDto.UserResponseDto;
import com.bada.bazaar.service.AuthService;
import com.bada.bazaar.util.JwtHelper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final SellerRepository sellerRepository;
  private final CustomerRepository customerRepository;
  private final AdminRepository adminRepository;


  @Override
  public UserResponseDto register(UserPostRequestDto userPostRequestDto) {
    return null;
  }


  @Override
  public String login(LoginRequest loginRequest) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    return JwtHelper.generateToken(loginRequest.getUsername());
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Seller seller = sellerRepository.findByUsername(username);
    if (seller != null) {
      return createUserDetails(seller.getUsername(), seller.getPassword(), "ROLE_SELLER");
    }

    Customer customer = customerRepository.findByUsername(username);
    if (customer != null) {
      return createUserDetails(customer.getUsername(), customer.getPassword(), "ROLE_CUSTOMER");
    }

    Admin admin = adminRepository.findByUsername(username);
    if (admin != null) {
      return createUserDetails(admin.getUsername(), admin.getPassword(), "ROLE_ADMIN");
    }

    throw new UsernameNotFoundException("User not found with username: " + username);
  }



  private UserDetails createUserDetails(String username, String password, String role) {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(role));
    return org.springframework.security.core.userdetails.User.builder()
      .username(username)
      .password(password)
      .authorities(authorities)
      .build();
  }
}

