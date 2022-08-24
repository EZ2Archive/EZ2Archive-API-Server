package com.ez2archive.common.exception.business;

import com.ez2archive.common.exception.AbstractBusinessException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends AbstractBusinessException
{
  public BadRequestException(String message)
  {
    super(HttpStatus.BAD_REQUEST, message);
  }
}
