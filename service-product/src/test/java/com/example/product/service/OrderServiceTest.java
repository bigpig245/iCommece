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
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
  private final PodamFactory PODAM = new PodamFactoryImpl();
  @InjectMocks
  private OrderService orderService;

  @Mock
  private CartRepository cartRepository;
  @Mock
  private OrderRepository orderRepository;
  @Mock
  private OrderMapper orderMapper;

  @Test
  public void shouldNotSaveOrder() {
    RequestOrderDto requestOrderDto = PODAM.manufacturePojo(RequestOrderDto.class);
    given(cartRepository.findAllByCustomerId(requestOrderDto.getCustomerId())).willReturn(Collections.emptyList());
    Assertions.assertThatThrownBy(() -> orderService.saveOrder(requestOrderDto))
        .isInstanceOf(CustomException.class)
        .hasMessage(SPMessage.CART_ITEMS_NOT_FOUND.getMessage());
  }

  @Test
  public void shouldSaveOrder() {
    RequestOrderDto requestOrderDto = PODAM.manufacturePojo(RequestOrderDto.class);
    Cart cart = PODAM.manufacturePojo(Cart.class);
    given(cartRepository.findAllByCustomerId(requestOrderDto.getCustomerId()))
        .willReturn(Arrays.asList(cart));

    OrderDetail orderDetail = PODAM.manufacturePojo(OrderDetail.class);
    given(orderMapper.cartToOrderDetail(cart)).willReturn(orderDetail);

    Order order = PODAM.manufacturePojo(Order.class);
    given(orderMapper.requestOrderDtoToOrder(requestOrderDto)).willReturn(order);

    OrderDto orderDto = PODAM.manufacturePojo(OrderDto.class);
    given(orderMapper.orderToOrderDto(order)).willReturn(orderDto);

    Assertions.assertThat(orderService.saveOrder(requestOrderDto))
        .isEqualTo(orderDto);
    verify(orderRepository).save(order);
  }

}
