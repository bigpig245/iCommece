package com.example.order.service;

import com.example.order.dto.CartDto;
import com.example.order.dto.RequestCartDto;
import com.example.order.rest.ProductRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.order.utils.RestClientHelper;

import java.util.List;

@Service
public class CartService {
  @Autowired
  private ProductRest productRest;

  public CartDto saveCart(Long customerId, RequestCartDto cartDto) {
    return RestClientHelper.execute(productRest.addToCart(customerId, cartDto)).body();
  }

  public List<CartDto> findAllCart(Long customerId) {
    return RestClientHelper.execute(productRest.viewCart(customerId)).body();
  }

}
