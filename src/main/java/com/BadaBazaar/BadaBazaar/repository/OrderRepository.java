package com.BadaBazaar.BadaBazaar.repository;

import com.BadaBazaar.BadaBazaar.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Ordered,Integer> {

}
