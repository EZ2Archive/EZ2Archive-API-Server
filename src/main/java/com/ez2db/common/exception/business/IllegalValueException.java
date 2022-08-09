package com.ez2db.common.exception.business;

public class IllegalValueException extends BadRequestException
{
  public IllegalValueException(String message)
  {
    super(message);
  }
}
