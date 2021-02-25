package com.example.order.rest;

import com.example.order.dto.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRest {

  @GET("v1/products/{productId}")
  Call<ProductDto> getProduct(@Path("productId") Long productId);

  @GET("v1/products")
  Call<PageDataDto<ProductDto>> search(
      @Query("page") int page,
      @Query("limit") int limit,
      @Query("sort_by") String sortBy,
      @Query("sort_order") String order,
      @Query("name") String name,
      @Query("brand") String brand,
      @Query("color") String color,
      @Query("unit_price_from") BigDecimal unitPriceFrom,
      @Query("unit_price_to") BigDecimal unitPriceTo);

  @POST("v1/carts/{customerId}/items")
  Call<CartDto> addToCart(@Path("customerId") Long customerId, @Body RequestCartDto dto);

  @GET("v1/carts/{customerId}/items")
  Call<List<CartDto>> viewCart(@Path("customerId") Long customerId);

  @POST("v1/orders")
  Call<OrderDto> placeOrder(@Body RequestOrderDto dto);
}
