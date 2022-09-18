package com.ez2archive.common.crypt;

public interface HashCryptor
{
  byte [] encrypt(String message, Object salt);
}
