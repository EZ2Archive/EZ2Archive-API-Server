package com.ez2archive.common.validator;

import com.ez2archive.common.condition.CommonCondition;
import com.ez2archive.dto.auth.RequestPasswordModifyDTO;

public class DefaultPasswordModifyValidator implements Validator<RequestPasswordModifyDTO>
{
  @Override
  public boolean isValid(RequestPasswordModifyDTO dto)
  {
    return isPasswordValid(dto.getPassword())
      && isTokenValid(dto.getToken());
  }

  private boolean isPasswordValid(String password)
  {
    return CommonCondition.isPasswordValid(password);
  }

  private boolean isTokenValid(String token)
  {
    return token != null
      && !token.isEmpty();
  }
}
