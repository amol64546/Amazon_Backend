package com.BadaBazaar.BadaBazaar.repository;

import com.BadaBazaar.BadaBazaar.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
