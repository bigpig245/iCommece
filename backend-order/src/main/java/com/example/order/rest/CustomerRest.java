package com.example.order.rest;

import com.example.order.dto.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface CustomerRest {

  @POST("v1/users/login")
  Call<UserDto> login(@Body UserLoginDto dto);

  @POST("v1/users/activity")
  Call<Void> saveActivity(@Body UserActivityDto userActivityDto);

}
