package com.ez2db.ez2db.common.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler
{
  @ExceptionHandler(Exception.class)
  public <T> Ez2DBResponse<T> handlerCommonException()
  {
    return Ez2DBResponse.<T>builder()
      .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .data(null)
      .build();
  }
}
