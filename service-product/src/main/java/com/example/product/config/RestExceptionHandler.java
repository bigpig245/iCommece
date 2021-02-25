package com.example.product.config;

import com.example.product.dto.ErrorDto;
import com.example.product.exception.CustomException;
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
