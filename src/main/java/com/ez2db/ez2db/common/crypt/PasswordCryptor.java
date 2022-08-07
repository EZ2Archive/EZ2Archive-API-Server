package com.ez2db.ez2db.common.crypt;

public interface PasswordCryptor
{
  byte [] encrypt(String message, long salt);
}
