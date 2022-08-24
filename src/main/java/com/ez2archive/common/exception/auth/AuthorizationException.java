package com.ez2archive.common.exception.auth;

import com.ez2archive.common.exception.AbstractBusinessException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends AbstractBusinessException
{
  public AuthorizationException(String message)
  {
    super(HttpStatus.UNAUTHORIZED, message);
  }
}
