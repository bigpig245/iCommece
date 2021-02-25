package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.dto.RequestOrderDto;
import com.example.order.dto.UserDto;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @PostMapping()
  public ResponseEntity<OrderDto> placeOrder(@AuthenticationPrincipal UserDto userDto) {
    RequestOrderDto requestOrderDto = new RequestOrderDto();
    requestOrderDto.setCustomerId(userDto.getId());
    requestOrderDto.setCustomerName(userDto.getName());
    requestOrderDto.setCustomerAddress(userDto.getAddress());
    requestOrderDto.setCustomerPhoneNumber(userDto.getPhoneNumber());
    return ResponseEntity.ok(orderService.placeOrder(requestOrderDto));
  }

}
