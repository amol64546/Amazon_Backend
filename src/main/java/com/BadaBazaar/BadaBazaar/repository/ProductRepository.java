package com.BadaBazaar.BadaBazaar.repository;

import com.BadaBazaar.BadaBazaar.enums.ProductCategory;
import com.BadaBazaar.BadaBazaar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAllByProductCategory(ProductCategory productCategory);
}
