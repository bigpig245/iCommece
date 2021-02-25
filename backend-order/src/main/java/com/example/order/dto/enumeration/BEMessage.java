package com.example.order.dto.enumeration;

import org.springframework.http.HttpStatus;

public enum BEMessage {
  OK("0", "SUCCESS", HttpStatus.OK),
  RESOURCE_NOT_FOUND("ERR_PU_1001", "Product not found", HttpStatus.BAD_REQUEST),
  UNKNOWN_ERROR("ERR_PU_1002", "Unknown error", HttpStatus.BAD_REQUEST);

  BEMessage(String code, String message, HttpStatus httpStatus) {
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
