package com.example.product.dto;

import com.sun.istack.NotNull;

import javax.annotation.Nonnegative;
import java.math.BigDecimal;

public class UnitPriceDto {
  @NotNull
  @Nonnegative
  private BigDecimal newPrice;

  public BigDecimal getNewPrice() {
    return newPrice;
  }

  public void setNewPrice(BigDecimal newPrice) {
    this.newPrice = newPrice;
  }
}
