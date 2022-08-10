package com.ez2db.common.exception.business;

public class ResourceNotFoundException extends BadRequestException
{
  public ResourceNotFoundException(String message)
  {
    super(message);
  }
}
