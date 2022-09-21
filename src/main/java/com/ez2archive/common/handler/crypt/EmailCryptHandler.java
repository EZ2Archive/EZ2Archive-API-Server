package com.ez2archive.common.handler.crypt;

import com.ez2archive.common.crypt.HashCryptor;
import com.ez2archive.common.crypt.HexConverter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailCryptHandler
{
  private final HashCryptor hashCryptor;
  private final Object emailSalt;

  public String encode(String message)
  {
    return HexConverter.encode( hashCryptor.encrypt(message, emailSalt) );
  }
}
