package com.ez2achieve.common.exception.auth;

import com.ez2achieve.common.exception.AbstractBusinessException;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends AbstractBusinessException
{
  public AuthenticationException(String message)
  {
    super(HttpStatus.UNAUTHORIZED, message);
  }
}
