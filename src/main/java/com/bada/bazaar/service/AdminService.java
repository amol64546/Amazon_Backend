package com.bada.bazaar.service;

import com.bada.bazaar.entity.User;
import com.bada.bazaar.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
  Page<User> getUsersByRole(Role role, Pageable pageable, HttpServletRequest request);
}
