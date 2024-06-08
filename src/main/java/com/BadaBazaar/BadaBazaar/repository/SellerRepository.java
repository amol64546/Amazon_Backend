package com.BadaBazaar.BadaBazaar.repository;

import com.BadaBazaar.BadaBazaar.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

}
