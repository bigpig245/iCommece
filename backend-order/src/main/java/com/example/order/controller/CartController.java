package com.example.order.controller;

import com.example.order.dto.CartDto;
import com.example.order.dto.RequestCartDto;
import com.example.order.dto.UserDto;
import com.example.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {
  @Autowired
  private CartService cartService;

  @PostMapping()
  public ResponseEntity<CartDto> addToCart(@AuthenticationPrincipal UserDto userDto, @RequestBody RequestCartDto dto) {
    return ResponseEntity.ok(cartService.saveCart(userDto.getId(), dto));
  }

  @GetMapping()
  public ResponseEntity<List<CartDto>> viewCart(@AuthenticationPrincipal UserDto userDto) {
    return ResponseEntity.ok(cartService.findAllCart(userDto.getId()));
  }

}
