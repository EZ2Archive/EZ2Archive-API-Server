package com.ez2db.common.handler.crypt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SuppressWarnings("RedundantThrows")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PasswordCryptHandlerTest
{
  @Autowired
  private PasswordCryptHandler handler;

  @BeforeEach
  void setUp()
  {
  }

  @AfterEach
  void tearDown()
  {
  }

  @Test
  public void encode() throws Exception
  {
    // given
    final String message = "ksh1042";
    final long salt = 0L;
    final String expected = "abbc088b9a9a8edecab385340bb67a9aae9d01ddca8626c16d45ccf6d172d29eb1c2694ccce719d79e31c9241fb9762daa4f6c8f292e13de82b7365d12fc93cf";

    // when
    final String actual = handler.encode(message, salt);

    // then
    Assertions.assertEquals(expected, actual);
  }
}