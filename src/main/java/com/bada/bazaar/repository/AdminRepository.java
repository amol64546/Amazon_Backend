package com.bada.bazaar.repository;

import com.bada.bazaar.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

  Admin findByUsername(String username);
}
