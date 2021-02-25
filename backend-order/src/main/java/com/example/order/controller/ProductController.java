package com.example.order.controller;

import com.example.order.dto.PageDataDto;
import com.example.order.dto.ProductDto;
import com.example.order.dto.SearchRequestDto;
import com.example.order.dto.UserDto;
import com.example.order.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping(value = "/{productId}")
  public ResponseEntity<ProductDto> getProduct(@AuthenticationPrincipal UserDto userDto,
      @PathVariable Long productId) {
    return ResponseEntity.ok(productService.findProduct(userDto, productId));
  }

  @GetMapping
  public ResponseEntity<PageDataDto<ProductDto>> search(
      @AuthenticationPrincipal UserDto userDto,
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "limit", defaultValue = "10") int limit,
      @RequestParam(value = "sort_by", defaultValue = "lastModifiedDate") String sortBy,
      @RequestParam(value = "sort_order", defaultValue = "desc") String order,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "brand", required = false) String brand,
      @RequestParam(value = "color", required = false) String color,
      @RequestParam(value = "unit_price_from", required = false) BigDecimal unitPriceFrom,
      @RequestParam(value = "unit_price_to", required = false) BigDecimal unitPriceTo
  ) throws JsonProcessingException {
    SearchRequestDto dto = new SearchRequestDto();
    dto.setName(name);
    dto.setBrand(brand);
    dto.setColor(color);
    dto.setUnitPriceFrom(unitPriceFrom);
    dto.setUnitPriceTo(unitPriceTo);
    dto.setPage(page);
    dto.setLimit(limit);
    dto.setSortOrder(order);
    dto.setSortBy(sortBy);
    return ResponseEntity.ok(productService.search(userDto, dto));
  }

}
