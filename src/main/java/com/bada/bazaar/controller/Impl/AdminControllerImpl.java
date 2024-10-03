package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.AdminController;
import com.bada.bazaar.entity.User;
import com.bada.bazaar.enums.Role;
import com.bada.bazaar.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

  private final AdminService adminService;


  @Override
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Page<User>> getUsersByRole(Role role, Pageable pageable, HttpServletRequest request) {
    log.info("[GET]: Request to get users by role: {}", role);
    return ResponseEntity.status(HttpStatus.OK)
      .body(adminService.getUsersByRole(role, pageable, request));
  }
}
