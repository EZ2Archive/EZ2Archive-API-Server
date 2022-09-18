package com.ez2archive.common.crypt;

import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
public class DefaultEmailCryptor implements HashCryptor
{
  private final String algorithm;

  @Override
  public byte[] encrypt(String message, Object salt)
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
