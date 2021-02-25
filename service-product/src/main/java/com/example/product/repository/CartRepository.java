package com.example.product.repository;

import com.example.product.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

  Cart findByCustomerIdAndProductId(Long customerId, Long productId);

  List<Cart> findAllByCustomerId(Long customerId);

  void deleteAllByCustomerId(Long customerId);
}
