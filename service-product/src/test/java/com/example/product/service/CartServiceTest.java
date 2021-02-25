package com.example.product.service;

import com.example.product.domain.Cart;
import com.example.product.domain.Product;
import com.example.product.dto.CartDto;
import com.example.product.dto.RequestCartDto;
import com.example.product.exception.CustomException;
import com.example.product.mapper.CartMapper;
import com.example.product.repository.CartRepository;
import com.example.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
  private final PodamFactory PODAM = new PodamFactoryImpl();
  @Mock
  private CartRepository cartRepository;
  @Mock
  private ProductRepository productRepository;
  @Mock
  private CartMapper cartMapper;
  @InjectMocks
  private CartService cartService;

  @Test
  public void shouldNotSaveCart() {
    Long id = 1L;
    RequestCartDto dto = new RequestCartDto();
    Assertions.assertThatThrownBy(() -> cartService.saveCart(id, dto))
        .isInstanceOf(CustomException.class)
        .hasMessage("Product not found");
  }

  @Test
  public void shouldSaveNewCart() {
    Long id = 1L;
    RequestCartDto dto = new RequestCartDto(2L, 3L);

    Product product = PODAM.manufacturePojo(Product.class);
    given(productRepository.findById(dto.getProductId()))
        .willReturn(Optional.of(product));
    CartDto cartDto = PODAM.manufacturePojo(CartDto.class);
    given(cartMapper.cartToCartDto(any())).willReturn(cartDto);
    Assertions.assertThat(cartService.saveCart(id, dto)).isEqualTo(cartDto);
    ArgumentCaptor<Cart> cartArgumentCaptor = ArgumentCaptor.forClass(Cart.class);
    verify(cartRepository).save(cartArgumentCaptor.capture());
    Assertions.assertThat(cartArgumentCaptor.getValue())
        .extracting("customerId", "quantity", "productId", "product")
        .containsExactly(id, dto.getQuantity(), dto.getProductId(), product);
  }

  @Test
  public void shouldUpdateCart() {
    Long id = 1L;
    RequestCartDto dto = new RequestCartDto(2L, 3L);

    Product product = PODAM.manufacturePojo(Product.class);
    given(productRepository.findById(dto.getProductId()))
        .willReturn(Optional.of(product));
    Cart cart = PODAM.manufacturePojo(Cart.class);
    cart.setQuantity(4L);
    given(cartRepository.findByCustomerIdAndProductId(id, dto.getProductId()))
        .willReturn(cart);

    CartDto cartDto = PODAM.manufacturePojo(CartDto.class);
    given(cartMapper.cartToCartDto(cart)).willReturn(cartDto);
    Assertions.assertThat(cartService.saveCart(id, dto)).isEqualTo(cartDto);
    ArgumentCaptor<Cart> cartArgumentCaptor = ArgumentCaptor.forClass(Cart.class);
    verify(cartRepository).save(cart);
    Assertions.assertThat(cart.getQuantity()).isEqualTo(7L);
  }

  @Test
  public void shouldFindAllCart() {
    Long customerId = 1L;
    Cart cart = PODAM.manufacturePojo(Cart.class);
    CartDto cartDto = PODAM.manufacturePojo(CartDto.class);
    given(cartMapper.cartToCartDto(cart)).willReturn(cartDto);
    given(cartRepository.findAllByCustomerId(customerId))
        .willReturn(Arrays.asList(cart));
    Assertions.assertThat(cartService.findAllCart(customerId))
        .hasSize(1)
        .containsExactly(cartDto);
  }
}
