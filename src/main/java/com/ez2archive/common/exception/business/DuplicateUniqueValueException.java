package com.ez2archive.common.exception.business;

public class DuplicateUniqueValueException extends BadRequestException
{
  public DuplicateUniqueValueException(String message)
  {
    super(message);
  }
}
