package com.bada.bazaar.service.Impl;

import com.bada.bazaar.cache.SellerCache;
import com.bada.bazaar.entity.Customer;
import com.bada.bazaar.entity.Seller;
import com.bada.bazaar.entity.User;
import com.bada.bazaar.enums.Role;
import com.bada.bazaar.error.ApiException;
import com.bada.bazaar.error.ErrorConstants;
import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.repository.UserRepository;
import com.bada.bazaar.dto.request.UserLoginRequest;
import com.bada.bazaar.dto.request.UserRegisterRequestDto;
import com.bada.bazaar.dto.response.UserResponseDto;
import com.bada.bazaar.service.AuthService;
import com.bada.bazaar.util.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final SellerCache sellerCache;
  private final CustomerRepository customerRepository;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserResponseDto register(UserRegisterRequestDto userRegisterRequestDto) {
    Optional<User> user = userRepository.findByUsername(userRegisterRequestDto.getUsername());
    if (user.isPresent()) {
      throw new ApiException(ErrorConstants.USER_ALREADY_EXISTS);
    }

    User userEntity = modelMapper.map(userRegisterRequestDto, User.class);

    UserResponseDto userResponseDto;
    if (userRegisterRequestDto.getRole().name().equals(Role.SELLER.name())) {
      userResponseDto = createSellerUser(userRegisterRequestDto);
    } else {
      userResponseDto = createCustomerUser(userRegisterRequestDto);
    }
    userEntity.setId(userResponseDto.getId());
    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
    userRepository.save(userEntity);
    return userResponseDto;
  }


  public UserResponseDto createSellerUser(UserRegisterRequestDto userRegisterRequestDto) {
    Seller seller = modelMapper.map(userRegisterRequestDto, Seller.class);
    Seller sellerFromDb = sellerCache.saveSeller(seller);
    return modelMapper.map(sellerFromDb, UserResponseDto.class);
  }

  public UserResponseDto createCustomerUser(UserRegisterRequestDto userRegisterRequestDto) {
    Customer customer = modelMapper.map(userRegisterRequestDto, Customer.class);
    Customer customerFromDb = customerRepository.save(customer);
    return modelMapper.map(customerFromDb, UserResponseDto.class);
  }


  @Override
  public String login(UserLoginRequest userLoginRequest) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        userLoginRequest.getUsername(),
        userLoginRequest.getPassword()));
    Optional<User> user = userRepository.findByUsername(userLoginRequest.getUsername());
    if (user.isEmpty()) {
      throw new ApiException(ErrorConstants.USER_NOT_FOUND);
    }
    return JwtHelper.generateToken(user.get());
  }

}

