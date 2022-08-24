package com.ez2achieve.common.exception.auth;

import com.ez2achieve.common.exception.AbstractBusinessException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends AbstractBusinessException
{
  public AuthorizationException(String message)
  {
    super(HttpStatus.UNAUTHORIZED, message);
  }
}
