package com.example.week7springmvc.Repository;

import com.example.week7springmvc.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

    Product findProductById(Long product);
}
