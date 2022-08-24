package com.ez2achieve.common.exception.business;

public class IllegalValueException extends BadRequestException
{
  public IllegalValueException(String message)
  {
    super(message);
  }
}
