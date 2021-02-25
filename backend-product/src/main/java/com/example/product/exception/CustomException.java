package com.example.product.exception;

import com.example.product.dto.enumeration.BEMessage;

public class CustomException extends RuntimeException {
  private BEMessage BEMessage;

  public CustomException(BEMessage BEMessage) {
    this(BEMessage, null);
  }

  public CustomException(BEMessage BEMessage, Exception e) {
    this(BEMessage.getMessage(), e);
    this.BEMessage = BEMessage;
  }

  public CustomException(String message, Exception e) {
    super(message, e);
  }
  public CustomException(String message, String code, Exception e) {
    super(message, e);
  }

  public BEMessage getSuMessage() {
    return BEMessage;
  }

  public void setSuMessage(BEMessage BEMessage) {
    this.BEMessage = BEMessage;
  }
}
