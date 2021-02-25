package com.example.product.mapper;

import com.example.product.domain.Cart;
import com.example.product.dto.CartDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class CartMapperTest {
  private CartMapper cartMapper = Mappers.getMapper(com.example.product.mapper.CartMapper.class);
  private final PodamFactory PODAM = new PodamFactoryImpl();

  @Test
  public void shouldNotConvertCartToCartDto() {
    Assertions.assertThat(cartMapper.cartToCartDto(null)).isNull();
  }

  @Test
  public void shouldConvertCartToCartDto() {
    Cart cart = PODAM.manufacturePojo(Cart.class);
    CartDto cartDto = cartMapper.cartToCartDto(cart);
    Assertions.assertThat(cartDto).extracting("id", "customerId", "quantity",
        "productId", "productName", "productBrand",
        "productColor", "unitPrice")
        .containsExactly(cart.getId(), cart.getCustomerId(), cart.getQuantity(),
            cart.getProductId(), cart.getProduct().getName(), cart.getProduct().getBrand(),
            cart.getProduct().getColor(), cart.getProduct().getUnitPrice());
  }

}
