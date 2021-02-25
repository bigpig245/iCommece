package com.example.product.exception;

import com.example.product.dto.enumeration.SPMessage;

public class CustomException extends RuntimeException {
  private SPMessage SPMessage;

  public CustomException(SPMessage SPMessage) {
    this(SPMessage, null);
  }

  public CustomException(SPMessage SPMessage, Exception e) {
    this(SPMessage.getMessage(), e);
    this.SPMessage = SPMessage;
  }

  public CustomException(String message, Exception e) {
    super(message, e);
  }

  public SPMessage getSuMessage() {
    return SPMessage;
  }

  public void setSuMessage(SPMessage SPMessage) {
    this.SPMessage = SPMessage;
  }
}
