package com.example.product.service;

import com.example.product.domain.Cart;
import com.example.product.domain.Order;
import com.example.product.domain.OrderDetail;
import com.example.product.dto.OrderDto;
import com.example.product.dto.RequestOrderDto;
import com.example.product.dto.enumeration.SPMessage;
import com.example.product.exception.CustomException;
import com.example.product.mapper.OrderMapper;
import com.example.product.repository.CartRepository;
import com.example.product.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private OrderMapper orderMapper;

  @Transactional
  public OrderDto saveOrder(RequestOrderDto dto) {
    List<Cart> cartDtos = cartRepository.findAllByCustomerId(dto.getCustomerId());

    if (CollectionUtils.isEmpty(cartDtos)) {
      throw new CustomException(SPMessage.CART_ITEMS_NOT_FOUND);
    }

    List<OrderDetail> orderDetails = new ArrayList<>();
    BigDecimal totalAmount = new BigDecimal(0);
    for (Cart cart : cartDtos) {
      OrderDetail orderDetail = orderMapper.cartToOrderDetail(cart);
      orderDetail.setAmount(orderDetail.getUnitPrice().multiply(new BigDecimal(orderDetail.getQuantity())));
      totalAmount = totalAmount.add(orderDetail.getAmount());
      orderDetails.add(orderDetail);
    }

    Order order = orderMapper.requestOrderDtoToOrder(dto);
    order.setOrderDate(LocalDateTime.now());
    order.setOrderAmount(totalAmount);
    order.setOrderDetails(orderDetails);
    orderRepository.save(order);

    cartRepository.deleteAllByCustomerId(dto.getCustomerId());
    return orderMapper.orderToOrderDto(order);
  }

}
