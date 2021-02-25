package com.example.product.service;

import com.example.product.domain.Cart;
import com.example.product.domain.Product;
import com.example.product.dto.CartDto;
import com.example.product.dto.RequestCartDto;
import com.example.product.dto.enumeration.SPMessage;
import com.example.product.exception.CustomException;
import com.example.product.mapper.CartMapper;
import com.example.product.repository.CartRepository;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private CartMapper cartMapper;

  @Transactional
  public CartDto saveCart(Long customerId, RequestCartDto cartDto) {
    Product product = productRepository.findById(cartDto.getProductId())
        .orElseThrow(() -> new CustomException(SPMessage.RESOURCE_NOT_FOUND));
    Cart cart = cartRepository.findByCustomerIdAndProductId(customerId, cartDto.getProductId());
    if (cart == null) {
      cart = new Cart();
      cart.setCustomerId(customerId);
      cart.setQuantity(cartDto.getQuantity());
      cart.setProductId(cartDto.getProductId());
      cart.setProduct(product);
    } else {
      cart.setQuantity(cartDto.getQuantity() + cart.getQuantity());
    }
    cartRepository.save(cart);
    return cartMapper.cartToCartDto(cart);
  }

  public List<CartDto> findAllCart(Long customerId) {
    List<Cart> carts = cartRepository.findAllByCustomerId(customerId);
    return carts.stream().map(c -> cartMapper.cartToCartDto(c))
        .collect(Collectors.toList());
  }

}
