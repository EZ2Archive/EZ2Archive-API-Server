package com.ez2achieve.common.crypt;

public interface PasswordCryptor
{
  byte [] encrypt(String message, long salt);
}
