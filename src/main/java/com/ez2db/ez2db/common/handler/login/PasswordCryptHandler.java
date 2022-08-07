package com.ez2db.ez2db.common.handler.login;

import com.ez2db.ez2db.common.crypt.HexConverter;
import com.ez2db.ez2db.common.crypt.PasswordCryptor;

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
