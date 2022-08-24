package com.ez2achieve.common.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractBusinessException extends RuntimeException
{
  protected final HttpStatus status;

  protected final String excepted;

  protected AbstractBusinessException(HttpStatus status, String message)
  {
    super(message);
    this.status = status;
    this.excepted = this.getClass().getCanonicalName();
  }

  public HttpStatus getStatus()
  {
    return status;
  }

  public int getStatusValue()
  {
    return status.value();
  }

  public String getExcepted()
  {
    return excepted;
  }
}
