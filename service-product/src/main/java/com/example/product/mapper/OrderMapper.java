package com.example.product.mapper;

import com.example.product.domain.Cart;
import com.example.product.domain.Order;
import com.example.product.domain.OrderDetail;
import com.example.product.dto.OrderDetailDto;
import com.example.product.dto.OrderDto;
import com.example.product.dto.RequestOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

  @Mapping(target = "orderDetails", expression = "java(orderDetailsToOrderDetailDtos(order.getOrderDetails()))")
  OrderDto orderToOrderDto(Order order);

  @Mapping(target = "id", ignore = true)
  Order requestOrderDtoToOrder(RequestOrderDto orderDto);

  @Mapping(target = "unitPrice", source = "product.unitPrice")
  OrderDetail cartToOrderDetail(Cart cart);

  @Mapping(target = "productId", source = "product.id")
  @Mapping(target = "productName", source = "product.name")
  @Mapping(target = "productBrand", source = "product.brand")
  @Mapping(target = "productColor", source = "product.color")
  @Mapping(target = "orderId", source = "order.id")
  OrderDetailDto orderDetailToOrderDetailDto(OrderDetail orderDetail);

  List<OrderDetailDto> orderDetailsToOrderDetailDtos(List<OrderDetail> orderDetails);
}
