package com.example.order.config;

import com.example.order.dto.ErrorDto;
import com.example.order.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * exception handler for controller
 */
@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(value = {CustomException.class})
  @ResponseBody
  public ResponseEntity<ErrorDto> handleResourceUnauthorized(CustomException ex) {
    ErrorDto error = new ErrorDto(ex.getSuMessage().getCode(), ex.getSuMessage().getMessage());
    return ResponseEntity.status(ex.getSuMessage().getHttpStatus()).body(error);
  }

}
