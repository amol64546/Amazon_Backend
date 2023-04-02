package com.BadaBazaar.BadaBazaar.Repository;

import com.BadaBazaar.BadaBazaar.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM card c WHERE c.customer_id=:customerId")
//    void deleteAllByCustomerId(@Param("customerId") int customerId);
}
