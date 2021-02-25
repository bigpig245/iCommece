package com.example.product.service;

import com.example.product.dto.PageDataDto;
import com.example.product.dto.ProductDto;
import com.example.product.dto.SearchRequestDto;
import com.example.product.dto.UnitPriceDto;
import com.example.product.rest.ProductRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.product.utils.RestClientHelper;

@Service
public class ProductService {
  @Autowired
  private ProductRest productRest;

  public ProductDto saveProduct(ProductDto productDto) {
    return RestClientHelper.execute(productRest.saveProduct(productDto)).body();
  }

  public ProductDto findProduct(Long id) {
    return RestClientHelper.execute(productRest.getProduct(id)).body();
  }

  public Void updateProductPrice(Long id, UnitPriceDto priceDto) {
    return RestClientHelper.execute(productRest.updatePrice(id, priceDto)).body();
  }

  public PageDataDto<ProductDto> search(SearchRequestDto searchDto) {
    return RestClientHelper.execute(productRest.search(
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
  }

}
