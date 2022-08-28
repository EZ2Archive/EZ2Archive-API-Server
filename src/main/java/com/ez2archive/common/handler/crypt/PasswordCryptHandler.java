package com.ez2archive.common.handler.crypt;

import com.ez2archive.common.crypt.PasswordCryptor;
import com.ez2archive.common.crypt.HexConverter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordCryptHandler
{
  private final PasswordCryptor cryptor;

  public String encode(String password, long salt)
  {
    return HexConverter.encode( cryptor.encrypt(password, salt) );
  }
}