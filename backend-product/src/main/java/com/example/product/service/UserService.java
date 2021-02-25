package com.example.product.service;

import com.example.product.dto.UserDto;
import com.example.product.dto.UserLoginDto;
import com.example.product.rest.CustomerRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.product.utils.RestClientHelper;

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
