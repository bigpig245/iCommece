package com.example.product.controller;

import com.example.product.dto.PageDataDto;
import com.example.product.dto.ProductDto;
import com.example.product.dto.SearchRequestDto;
import com.example.product.dto.UnitPriceDto;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @PostMapping
  public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto dto) {
    return ResponseEntity.ok(productService.saveProduct(dto));
  }

  @GetMapping(value = "/{productId}")
  public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
    return ResponseEntity.ok(productService.findProduct(productId));
  }

  @GetMapping
  public ResponseEntity<PageDataDto<ProductDto>> search(
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "limit", defaultValue = "10") int limit,
      @RequestParam(value = "sort_by", defaultValue = "lastModifiedDate") String sortBy,
      @RequestParam(value = "sort_order", defaultValue = "desc") String order,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "brand", required = false) String brand,
      @RequestParam(value = "color", required = false) String color,
      @RequestParam(value = "unit_price_from", required = false) BigDecimal unitPriceFrom,
      @RequestParam(value = "unit_price_to", required = false) BigDecimal unitPriceTo
  ) {
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
    return ResponseEntity.ok(productService.search(dto));
  }

  @PatchMapping(value = "{productId}/update-price")
  public ResponseEntity<Void> updatePrice(@PathVariable Long productId,
      @RequestBody UnitPriceDto unitPriceDto) {
    productService.updateProductPrice(productId, unitPriceDto.getNewPrice());
    return ResponseEntity.noContent().build();
  }

}
