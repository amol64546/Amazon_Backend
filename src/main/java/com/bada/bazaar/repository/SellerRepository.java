package com.bada.bazaar.repository;

import com.bada.bazaar.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

  Seller findByUsername(String username);
}
