package com.ez2archive.common.handler.crypt;

import com.ez2archive.common.crypt.DoubleHashingCryptor;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
public class DefaultEmailCryptor implements DoubleHashingCryptor
{
  private final String algorithm;
  private final String salt;

  @Override
  public byte[] encode(String message)
  {
    byte [] result = null;

    try
    {
      final MessageDigest md = MessageDigest.getInstance( this.algorithm );

      // doubleHashing
      result = md.digest( (message + salt).getBytes(StandardCharsets.UTF_8) );

    }
    catch ( NoSuchAlgorithmException e )
    {
      e.printStackTrace();
    }

    return result;
  }
}
