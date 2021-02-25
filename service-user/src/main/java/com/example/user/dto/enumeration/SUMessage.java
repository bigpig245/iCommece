package com.example.user.dto.enumeration;

import org.springframework.http.HttpStatus;

public enum SUMessage {
  OK("0", "SUCCESS", HttpStatus.OK),
  RESOURCE_NOT_FOUND("ERR_SU_1001", "User is not found", HttpStatus.BAD_REQUEST);

  SUMessage(String code, String message, HttpStatus httpStatus) {
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
