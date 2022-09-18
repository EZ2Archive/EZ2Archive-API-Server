package com.ez2archive.common.handler.crypt;

import com.ez2archive.common.crypt.HashCryptor;
import com.ez2archive.common.crypt.HexConverter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordCryptHandler
{
  private final HashCryptor hashCryptor;

  public String encode(String password, long salt)
  {
    return HexConverter.encode( hashCryptor.encrypt(password, salt) );
  }
}
