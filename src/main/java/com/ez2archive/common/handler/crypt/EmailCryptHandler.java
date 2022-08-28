package com.ez2archive.common.handler.crypt;

import com.ez2archive.common.crypt.DoubleHashingCryptor;
import com.ez2archive.common.crypt.HexConverter;

public class EmailCryptHandler
{
  private final DoubleHashingCryptor doubleHashingCryptor;

  public EmailCryptHandler(DoubleHashingCryptor doubleHashingCryptor)
  {
    this.doubleHashingCryptor = doubleHashingCryptor;
  }

  public String encode(String message)
  {
    return HexConverter.encode( doubleHashingCryptor.encode(message) );
  }
}
