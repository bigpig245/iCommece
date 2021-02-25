package com.example.product.service;

import com.example.product.dto.UserActivityDto;
import com.example.product.rest.CustomerRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.product.utils.RestClientHelper;

@Service
public class UserActivityService {
  @Autowired
  private CustomerRest customerRest;

  public Void trackActivity(UserActivityDto userActivityDto) {
    return RestClientHelper.execute(customerRest.saveActivity(userActivityDto)).body();
  }
}
