package com.ez2achieve.common.exception.business;

public class ResourceNotFoundException extends BadRequestException
{
  public ResourceNotFoundException(String message)
  {
    super(message);
  }
}
