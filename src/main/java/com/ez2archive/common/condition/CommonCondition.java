package com.ez2archive.common.condition;

public class CommonCondition
{
  public static boolean isPasswordValid(String password)
  {
    return password != null
      && password.length() > 5
      && password.length() <= 500;
  }
}
