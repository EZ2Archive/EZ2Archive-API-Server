package com.ez2archive.common.crypt;

public interface PasswordCryptor
{
  byte [] encrypt(String message, long salt);
}
