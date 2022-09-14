package com.ez2archive.common.validator;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

class DefaultSignUpValidatorTest
{
  private static final Pattern EMAIL_REGEXP = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$");

  @Test
  void isValid()
  {
    List<String> emailList = List.of(
      "roman14@naver.com",
      "roman14@",
      "roman14@naver",
      "@naver.com",
      "@naver",
      "@.",
      "roman14@@naver.com",
      "roman14@naver..com"
    );

    emailList.forEach(
      s -> {
        System.out.println(s + "\t\t\t\t=" + isEmailValid(s));
      }
    );
  }

  private boolean isEmailValid(String email)
  {
    return EMAIL_REGEXP.matcher(email).matches();
  }
}