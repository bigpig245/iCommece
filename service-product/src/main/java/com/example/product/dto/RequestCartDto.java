package com.example.product.dto;

public class RequestCartDto {
  private Long productId;
  private Long quantity;

  public RequestCartDto() {
  }

  public RequestCartDto(Long productId, Long quantity) {
    this.productId = productId;
    this.quantity = quantity;
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

}