package com.example.order.service;

import com.example.order.dto.UserDto;
import com.example.order.dto.UserLoginDto;
import com.example.order.rest.CustomerRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.order.utils.RestClientHelper;

@Service
public class UserService {
  @Autowired
  private CustomerRest customerRest;

  public UserDto getUserInfo(String accessToken) {
    UserLoginDto userLoginDto = new UserLoginDto();
    userLoginDto.setAccessToken(accessToken);
    return RestClientHelper.execute(customerRest.login(userLoginDto)).body();
  }
}
