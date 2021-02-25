package com.example.product.rest;

import com.example.product.dto.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerRest {

  @POST("v1/users/login")
  Call<UserDto> login(@Body UserLoginDto dto);

  @POST("v1/users/activity")
  Call<Void> saveActivity(@Body UserActivityDto userActivityDto);

}
