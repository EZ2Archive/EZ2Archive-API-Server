package com.ez2archive.common.handler.crypt;

import com.ez2archive.common.crypt.DefaultHashCryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmailCryptHandlerTest
{
  private static final String HASH_ALGORITHM = "SHA-512";
  private static final Long EMAIL_SALT = -9531516846884L;
  private EmailCryptHandler emailCryptHandler;

  @BeforeEach
  void init()
  {
    emailCryptHandler = new EmailCryptHandler(new DefaultHashCryptor(HASH_ALGORITHM), EMAIL_SALT);
  }

  @Test
  void encode()
  {
    // Given
    final String email = "ez2archive1@gmail.com";
    final String expected = "5d71395dec03b0a28c4a31bf322f46538022996993416270380164387854e37c688d5d7b9a7dcc5792da4e7611f6d637ec6bf60d7f3d47415795c29b2ff65939";

    // When
    final String actual = emailCryptHandler.encode(email);

    // Then
    Assertions.assertEquals(expected, actual);
  }
}