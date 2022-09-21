package com.ez2archive.common.handler.crypt;

import com.ez2archive.common.crypt.DefaultHashCryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordCryptHandlerTest
{

  private static final String HASH_ALGORITHM = "SHA-512";
  private PasswordCryptHandler passwordCryptHandler;

  @BeforeEach
  void init()
  {
    passwordCryptHandler = new PasswordCryptHandler(new DefaultHashCryptor(HASH_ALGORITHM));
  }

  @Test
  void encode()
  {
    // Given
    final String password = "ez2archive";
    final long salt = -9531516846884L;
    final String expected = "5d9c466e700e1e4ae7bc73393132ff928a0c99c0bb4a450442069965c4eaa9e8af53ce3100892c3a48ad6fa39cb5aae009de3b6afc14745728b7c51137e55f8e";

    // When

    final String actual = passwordCryptHandler.encode(password, salt);

    // Then
    Assertions.assertEquals(actual, expected);
  }
}