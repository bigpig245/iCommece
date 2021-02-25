package com.example.product.repository;

import com.example.product.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  Order findByIdAndCustomerId(Long id, Long customerId);
}
