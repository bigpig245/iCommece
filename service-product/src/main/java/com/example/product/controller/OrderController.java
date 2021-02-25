package com.example.product.controller;

import com.example.product.dto.*;
import com.example.product.service.OrderService;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @PostMapping()
  public ResponseEntity<OrderDto> placeOrder(@RequestBody RequestOrderDto dto) {
    return ResponseEntity.ok(orderService.saveOrder(dto));
  }

}
