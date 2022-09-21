package com.ez2archive.common.validator;

import com.ez2archive.dto.auth.RequestSignUpDTO;

import java.util.regex.Pattern;

public class DefaultSignUpValidator implements Validator<RequestSignUpDTO>
{
  private static final Pattern ID_REG_EXP = Pattern.compile("^[A-za-z0-9]{5,12}$");
  private static final Pattern EMAIL_REGEXP = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$");

  @Override
  public boolean isValid(RequestSignUpDTO dto)
  {
    return isIdValid(dto.getUserId())
      && isPwdValid(dto.getPassword())
      && isNameValid(dto.getName())
      && isEmailValid(dto.getEmail());
  }

  private boolean isIdValid(String userId)
  {
    return userId != null
      && !userId.isEmpty()
      && ID_REG_EXP.matcher( userId ).matches();
  }

  private boolean isPwdValid(String password)
  {
    return password != null
      && password.length() > 5
      && password.length() <= 500;
  }

  private boolean isNameValid(String name)
  {
    return name != null
      && name.length() > 0
      && name.length() <= 10;
  }

  private boolean isEmailValid(String email)
  {
    return email != null
      && EMAIL_REGEXP.matcher(email).matches();
  }
}
