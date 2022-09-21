package com.ez2archive.common.validator;

import com.ez2archive.dto.auth.RequestLoginDTO;

public class DefaultLoginValidator implements Validator<RequestLoginDTO>
{
  @Override
  public boolean isValid(RequestLoginDTO requestLoginDTO)
  {
    return isIdValid(requestLoginDTO.getUserId())
      && isPasswordValid(requestLoginDTO.getPassword());
  }

  private boolean isIdValid(String userId)
  {
    return userId != null
      && !userId.isEmpty();
  }

  private boolean isPasswordValid(String password)
  {
    return password != null
      && !password.isEmpty();
  }
}
