package com.ez2archive.common.exception.business;

import com.ez2archive.common.exception.AbstractBusinessException;
import org.springframework.http.HttpStatus;

public class RequestTimeoutException extends AbstractBusinessException
{
  public RequestTimeoutException(String message)
  {
    super(HttpStatus.REQUEST_TIMEOUT, message);
  }

  public RequestTimeoutException()
  {
    this("만료된 요청입니다.");
  }
}
