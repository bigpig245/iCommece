package com.example.user.exception;

import com.example.user.dto.enumeration.SUMessage;

public class CustomException extends RuntimeException {
  private SUMessage suMessage;

  public CustomException(SUMessage suMessage) {
    this(suMessage, null);
  }

  public CustomException(SUMessage suMessage, Exception e) {
    this(suMessage.getMessage(), e);
    this.suMessage = suMessage;
  }

  public CustomException(String message, Exception e) {
    super(message, e);
  }

  public SUMessage getSuMessage() {
    return suMessage;
  }

  public void setSuMessage(SUMessage suMessage) {
    this.suMessage = suMessage;
  }
}
