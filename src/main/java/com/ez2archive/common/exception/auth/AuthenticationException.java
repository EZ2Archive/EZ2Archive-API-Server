package com.ez2archive.common.exception.auth;

import com.ez2archive.common.exception.AbstractBusinessException;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends AbstractBusinessException
{
  public AuthenticationException(String message)
  {
    super(HttpStatus.UNAUTHORIZED, message);
  }
}
