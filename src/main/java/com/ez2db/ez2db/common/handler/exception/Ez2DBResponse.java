package com.ez2db.ez2db.common.handler.exception;


import lombok.Builder;
import org.springframework.http.HttpStatus;

public class Ez2DBResponse<T>
{
  private final int status;
  private final String message;
  private final T data;

  @Builder
  public Ez2DBResponse(int status, String message, T data)
  {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public static <T> Ez2DBResponse<T> ok(String message, T data)
  {
    return Ez2DBResponse.<T>builder()
      .status(HttpStatus.OK.value())
      .message(message)
      .data(data)
      .build();
  }
}


