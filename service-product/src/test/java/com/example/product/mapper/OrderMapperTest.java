package com.example.product.mapper;

import com.example.product.domain.Cart;
import com.example.product.domain.Order;
import com.example.product.domain.OrderDetail;
import com.example.product.dto.OrderDto;
import com.example.product.dto.RequestOrderDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class OrderMapperTest {
  private OrderMapper orderMapper = Mappers.getMapper(com.example.product.mapper.OrderMapper.class);
  private final PodamFactory PODAM = new PodamFactoryImpl();

  @Test
  public void shouldNotConvertOrderToOrderDto() {
    Assertions.assertThat(orderMapper.orderToOrderDto(null)).isNull();
  }

  @Test
  public void shouldConvertOrderToOrderDto() {
    Order order = PODAM.manufacturePojo(Order.class);
    OrderDto orderDto = orderMapper.orderToOrderDto(order);
    Assertions.assertThat(orderDto).isEqualToComparingOnlyGivenFields(order, "orderDetails");
  }

  @Test
  public void shouldNotConvertRequestOrderToOrderDto() {
    Assertions.assertThat(orderMapper.requestOrderDtoToOrder(null)).isNull();
  }

  @Test
  public void shouldConvertRequestOrderToOrderDto() {
    RequestOrderDto requestOrderDto = PODAM.manufacturePojo(RequestOrderDto.class);
    Order order = orderMapper.requestOrderDtoToOrder(requestOrderDto);
    Assertions.assertThat(order).isEqualToComparingOnlyGivenFields(requestOrderDto);
  }

  @Test
  public void shouldNotConvertCartDtoToOrderDetail() {
    Assertions.assertThat(orderMapper.cartToOrderDetail(null)).isNull();
  }

  @Test
  public void shouldConvertCartDtoToOrderDetail() {
    Cart cart = PODAM.manufacturePojo(Cart.class);
    OrderDetail order = orderMapper.cartToOrderDetail(cart);
    Assertions.assertThat(order).isEqualToComparingOnlyGivenFields(cart);
  }

}
