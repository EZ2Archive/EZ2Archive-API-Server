package com.ez2archive.common.validator;

import com.ez2archive.dto.auth.RequestLoginDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultLoginValidatorTest
{
  private Validator<RequestLoginDTO> loginValidator;

  private final String userId = "ez2archive";
  private final String password = "password1234";
  @BeforeEach
  void init()
  {
    loginValidator = new DefaultLoginValidator();
  }

  @DisplayName("id null 케이스")
  @Test
  void isValidWithIdNull()
  {
    // Given
    RequestLoginDTO dto = new RequestLoginDTO();
    dto.setUserId(null);
    dto.setPassword(password);

    // When
    boolean validWithTrim = loginValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("password null 케이스")
  @Test
  void isValidWithPasswordNull()
  {
    // Given
    RequestLoginDTO dto = new RequestLoginDTO();
    dto.setUserId(userId);
    dto.setPassword(null);

    // When
    boolean validWithTrim = loginValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("정상 케이스")
  @Test
  void isValid()
  {
    // Given
    RequestLoginDTO dto = new RequestLoginDTO();
    dto.setUserId(userId);
    dto.setPassword(password);

    // When
    boolean validWithTrim = loginValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertTrue(validWithTrim);
  }
}