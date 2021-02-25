package com.example.order.service;

import com.example.order.dto.OrderDto;
import com.example.order.dto.RequestOrderDto;
import com.example.order.rest.ProductRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.order.utils.RestClientHelper;

@Service
public class OrderService {
  @Autowired
  private ProductRest productRest;

  public OrderDto placeOrder(RequestOrderDto dto) {
    return RestClientHelper.execute(productRest.placeOrder(dto)).body();
  }

}
