package com.example.product.rest;

import com.example.product.dto.PageDataDto;
import com.example.product.dto.ProductDto;
import com.example.product.dto.UnitPriceDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.math.BigDecimal;

public interface ProductRest {

  @POST("v1/products")
  Call<ProductDto> saveProduct(@Body ProductDto dto);

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

  @PATCH("v1/products/{productId}/update-price")
  Call<Void> updatePrice(@Path("productId") Long productId, @Body UnitPriceDto dto);

}
