package com.bada.bazaar.service.Impl;

import com.bada.bazaar.entity.Customer;
import com.bada.bazaar.entity.Seller;
import com.bada.bazaar.entity.UserEntity;
import com.bada.bazaar.enums.Role;
import com.bada.bazaar.exception.ApiException;
import com.bada.bazaar.exception.ErrorConstants;
import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.repository.SellerRepository;
import com.bada.bazaar.repository.UserRepository;
import com.bada.bazaar.requestDto.UserLoginRequest;
import com.bada.bazaar.requestDto.UserPostRequestDto;
import com.bada.bazaar.responseDto.UserResponseDto;
import com.bada.bazaar.service.UserService;
import com.bada.bazaar.util.JwtHelper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final SellerRepository sellerRepository;
  private final CustomerRepository customerRepository;
  private final ModelMapper modelMapper;

  @Override
  public UserResponseDto register(UserPostRequestDto userPostRequestDto) {
    UserEntity user = userRepository.findByUsername(userPostRequestDto.getUsername());
    if (ObjectUtils.isNotEmpty(user)) {
      throw new ApiException(ErrorConstants.USER_ALREADY_EXISTS);
    }

    //Todo: need to set userId
    UserEntity userEntity = modelMapper.map(userPostRequestDto, UserEntity.class);
    userRepository.save(userEntity);

    if (userPostRequestDto.getRole().name().equals(Role.SELLER.name())) {
      return createSellerUser(userPostRequestDto);
    }
    return createCustomerUser(userPostRequestDto);
  }

  public UserResponseDto createSellerUser(UserPostRequestDto userPostRequestDto) {
    Seller seller = modelMapper.map(userPostRequestDto, Seller.class);
    Seller sellerFromDb = sellerRepository.save(seller);
    return modelMapper.map(sellerFromDb, UserResponseDto.class);
  }

  public UserResponseDto createCustomerUser(UserPostRequestDto userPostRequestDto) {
    Customer customer = modelMapper.map(userPostRequestDto, Customer.class);
    Customer customerFromDb = customerRepository.save(customer);
    return modelMapper.map(customerFromDb, UserResponseDto.class);
  }


  @Override
  public String login(UserLoginRequest userLoginRequest) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(),
        userLoginRequest.getPassword()));
    return JwtHelper.generateToken(userLoginRequest.getUsername());
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    UserEntity user = userRepository.findByUsername(username);
    if (ObjectUtils.isNotEmpty(user)) {
      return createUserDetails(user.getUsername(), user.getPassword(),
        String.valueOf(user.getRole()));
    }
    throw new ApiException(ErrorConstants.USER_NOT_FOUND);
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

