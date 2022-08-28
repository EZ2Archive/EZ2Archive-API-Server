package com.ez2archive.common.validator;

import com.ez2archive.entity.Email;

import java.util.regex.Pattern;

public class DefaultEmailValidator implements Validator<Email>
{
  private static final Pattern EMAIL_REGEXP = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");

  @Override
  public boolean isValid(Email email)
  {
    throw new UnsupportedOperationException();
  }

  public boolean isValidEmail(String address)
  {
    return EMAIL_REGEXP.matcher( address ).matches();
  }
}
