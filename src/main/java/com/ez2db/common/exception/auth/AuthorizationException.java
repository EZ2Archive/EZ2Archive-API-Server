package com.ez2db.common.exception.auth;

import com.ez2db.common.exception.AbstractBusinessException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends AbstractBusinessException
{
  public AuthorizationException(String message)
  {
    super(HttpStatus.UNAUTHORIZED, message);
  }
}
