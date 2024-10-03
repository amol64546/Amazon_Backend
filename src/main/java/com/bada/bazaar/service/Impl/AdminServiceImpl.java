package com.bada.bazaar.service.Impl;

import com.bada.bazaar.entity.User;
import com.bada.bazaar.enums.Role;
import com.bada.bazaar.repository.UserRepository;
import com.bada.bazaar.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

  private final UserRepository userRepository;

  @Override
  public Page<User> getUsersByRole(Role role, Pageable pageable, HttpServletRequest request) {
    User user = new User();
    user.setRole(role);
    Example<User> example = Example.of(user);
    return userRepository.findAll(example, pageable);
  }
}
