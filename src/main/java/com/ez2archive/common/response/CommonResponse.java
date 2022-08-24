package com.ez2archive.common.response;


import lombok.Builder;

public class CommonResponse<T>
{
  private final String message;
  private final T data;

  @Builder
  public CommonResponse(String message, T data)
  {
    this.message = message;
    this.data = data;
  }

  public static <T> CommonResponse<T> success(T data)
  {
    return CommonResponse.<T>builder()
      .message("success")
      .data(data)
      .build();
  }

  public static <T> CommonResponse<T> success()
  {
    return success(null);
  }

  public static <E extends Throwable> CommonResponse<E> failed(E e)
  {
    return CommonResponse.<E>builder()
      .message(e.getMessage())
      .data(null)
      .build();
  }

  public String getMessage()
  {
    return message;
  }

  public T getData()
  {
    return data;
  }
}


