package com.BadaBazaar.BadaBazaar.repository;

import com.BadaBazaar.BadaBazaar.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
