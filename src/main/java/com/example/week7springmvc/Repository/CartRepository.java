package com.example.week7springmvc.Repository;

import com.example.week7springmvc.Model.Cart;
import com.example.week7springmvc.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT * FROM cart WHERE user_id = :userId AND product_id = :productId", nativeQuery = true)
    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);

    @Query(value = "SELECT * FROM cart WHERE user_id = :userId ", nativeQuery = true)
    List<Cart> findCartsy(Long userId);


}
