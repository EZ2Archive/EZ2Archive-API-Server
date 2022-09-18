package com.ez2archive.common.crypt;

import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
public class DefaultHashCryptor implements HashCryptor
{
  private final String algorithm;

  public byte [] encrypt(String message, Object salt)
  {
    byte [] result = null;

    try
    {
      final MessageDigest md = MessageDigest.getInstance( this.algorithm );

      result = md.digest( (message + salt.toString()).getBytes(StandardCharsets.UTF_8) );
    }
    catch ( NoSuchAlgorithmException e )
    {
      e.printStackTrace();
    }

    return result;
  }
}
