package com.example.product.dto;

import java.math.BigDecimal;

public class UnitPriceDto {
  private BigDecimal newPrice;

  public BigDecimal getNewPrice() {
    return newPrice;
  }

  public void setNewPrice(BigDecimal newPrice) {
    this.newPrice = newPrice;
  }
}
