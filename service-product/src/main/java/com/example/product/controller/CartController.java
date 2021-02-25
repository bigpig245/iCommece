package com.example.product.controller;

import com.example.product.dto.CartDto;
import com.example.product.dto.RequestCartDto;
import com.example.product.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {
  @Autowired
  private CartService cartService;

  @PostMapping("/{customerId}/items")
  public ResponseEntity<CartDto> addToCart(@PathVariable Long customerId, @RequestBody RequestCartDto dto) {
    return ResponseEntity.ok(cartService.saveCart(customerId, dto));
  }

  @GetMapping("/{customerId}/items")
  public ResponseEntity<List<CartDto>> viewCart(@PathVariable Long customerId) {
    return ResponseEntity.ok(cartService.findAllCart(customerId));
  }

}
