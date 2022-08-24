package com.ez2archive.common.exception.business;

public class ResourceNotFoundException extends BadRequestException
{
  public ResourceNotFoundException(String message)
  {
    super(message);
  }
}
