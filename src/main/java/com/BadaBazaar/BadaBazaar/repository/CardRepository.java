package com.BadaBazaar.BadaBazaar.repository;

import com.BadaBazaar.BadaBazaar.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {


}
