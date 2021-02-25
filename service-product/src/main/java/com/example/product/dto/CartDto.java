package com.example.product.dto;

import java.math.BigDecimal;
import java.util.Optional;

public class CartDto {
  private Long id;
  private Long customerId;
  private Long quantity;
  private Long productId;
  private String productName;
  private String productBrand;
  private String productColor;
  private BigDecimal unitPrice;
  private BigDecimal amount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductBrand() {
    return productBrand;
  }

  public void setProductBrand(String productBrand) {
    this.productBrand = productBrand;
  }

  public String getProductColor() {
    return productColor;
  }

  public void setProductColor(String productColor) {
    this.productColor = productColor;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  public BigDecimal getAmount() {
    return Optional.ofNullable(unitPrice)
        .map(price -> price.multiply(new BigDecimal(quantity)))
        .orElse(new BigDecimal(0));
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}