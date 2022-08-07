package com.ez2db.ez2db.common.crypt;

import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
public class DefaultPasswordCryptor implements PasswordCryptor
{
  private final String algorithm;

  public byte [] encrypt(String message, long salt)
  {
    byte [] result = null;

    try
    {
      final MessageDigest md = MessageDigest.getInstance( this.algorithm );

      result = md.digest( (message + salt).getBytes(StandardCharsets.UTF_8) );
    }
    catch ( NoSuchAlgorithmException e )
    {
      e.printStackTrace();
    }

    return result;
  }
}
