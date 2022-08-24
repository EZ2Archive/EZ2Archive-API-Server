package com.ez2achieve.common.handler.crypt;

import com.ez2achieve.common.crypt.PasswordCryptor;
import com.ez2achieve.common.crypt.HexConverter;

public class PasswordCryptHandler
{
  private final PasswordCryptor cryptor;

  public PasswordCryptHandler(PasswordCryptor cryptor)
  {
    this.cryptor = cryptor;
  }

  public String encode(String password, long salt)
  {
    return HexConverter.encode( cryptor.encrypt(password, salt) );
  }
}
