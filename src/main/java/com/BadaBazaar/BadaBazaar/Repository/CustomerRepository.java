package com.BadaBazaar.BadaBazaar.Repository;

import com.BadaBazaar.BadaBazaar.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByEmail(String email);
}
