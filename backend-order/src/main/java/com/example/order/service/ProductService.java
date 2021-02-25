package com.example.order.service;

import com.example.order.dto.*;
import com.example.order.dto.enumeration.Action;
import com.example.order.rest.CustomerRest;
import com.example.order.rest.ProductRest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.order.utils.Constants;
import com.example.order.utils.RestClientHelper;

import java.time.LocalDateTime;

@Service
public class ProductService {
  @Autowired
  private ProductRest productRest;
  @Autowired
  private CustomerRest customerRest;

  public ProductDto findProduct(UserDto userDto, Long id) {
    ProductDto productDto = RestClientHelper.execute(productRest.getProduct(id)).body();
    if (userDto != null) {
      UserActivityDto userActivityDto = new UserActivityDto();
      userActivityDto.setAction(Action.SEARCH);
      userActivityDto.setQuery(id.toString());
      userActivityDto.setTrackedDate(LocalDateTime.now());
      userActivityDto.setUserId(userDto.getId());
      customerRest.saveActivity(userActivityDto);
    }
    return productDto;
  }

  public PageDataDto<ProductDto> search(UserDto userDto, SearchRequestDto searchDto) throws JsonProcessingException {
    PageDataDto<ProductDto> pageDataDto = RestClientHelper.execute(productRest.search(
        searchDto.getPage(),
        searchDto.getLimit(),
        searchDto.getSortBy(),
        searchDto.getSortOrder(),
        searchDto.getName(),
        searchDto.getBrand(),
        searchDto.getColor(),
        searchDto.getUnitPriceFrom(),
        searchDto.getUnitPriceTo()
    )).body();

    if (userDto != null) {
      UserActivityDto userActivityDto = new UserActivityDto();
      userActivityDto.setAction(Action.SEARCH);
      userActivityDto.setQuery(Constants.MAPPER.writeValueAsString(searchDto));
      userActivityDto.setTrackedDate(LocalDateTime.now());
      userActivityDto.setUserId(userDto.getId());
      customerRest.saveActivity(userActivityDto);
    }
    return pageDataDto;
  }

}
