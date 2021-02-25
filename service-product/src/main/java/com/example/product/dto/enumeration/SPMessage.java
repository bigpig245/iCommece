package com.example.product.dto.enumeration;

import org.springframework.http.HttpStatus;

public enum SPMessage {
  OK("0", "SUCCESS", HttpStatus.OK),
  RESOURCE_NOT_FOUND("ERR_PU_1001", "Product not found", HttpStatus.BAD_REQUEST),
  CART_ITEMS_NOT_FOUND("ERR_PU_1002", "Cart items not found", HttpStatus.BAD_REQUEST);

  SPMessage(String code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  private final String code;

  private final String message;

  private final HttpStatus httpStatus;

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
