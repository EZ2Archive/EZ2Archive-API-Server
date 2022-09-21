package com.ez2archive.common.validator;

import com.ez2archive.dto.auth.RequestSignUpDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

class DefaultSignUpValidatorTest
{
  private static final Pattern EMAIL_REGEXP = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$");
  private static final Pattern ID_REG_EXP = Pattern.compile("^[A-za-z0-9]{4,12}$");

  private Validator<RequestSignUpDTO> signUpValidator;

  @BeforeEach
  void init()
  {
    signUpValidator = new DefaultSignUpValidator();
  }

  @DisplayName("email 유효성 케이스")
  @Test
  void isValidEmailExp()
  {
    // Given
    String success = "ez2archive1@gmail.com";
    List<String> failList = List.of(
      "ez2archive1@",
      "ez2archive1@gmail",
      "@gmail.com",
      "@gmail",
      "@.",
      "ez2archive1@@gmail.com",
      "ez2archive1@gmail..com"
    );

    // When Then
    failList.forEach( email -> Assertions.assertFalse( EMAIL_REGEXP.matcher(email).matches() ) );
    Assertions.assertTrue( EMAIL_REGEXP.matcher(success).matches() );
  }

  @DisplayName("userId 유효성 케이스")
  @Test
  void isValidUserIdExp()
  {
    List<String> successList = List.of(
      "em44",
      "roman14",
      "twine21mk",
      "emp4445",
      "opt14233"
    );
    List<String> failList    = List.of(
      "abc",
      "ekfnwoek10023",
      "en@kk14",
      "#emp0042",
      "user001!"
    );

    successList.forEach( userId -> Assertions.assertTrue( ID_REG_EXP.matcher(userId).matches() ) );
    failList.forEach( userId -> Assertions.assertFalse( ID_REG_EXP.matcher(userId).matches() ) );
  }

  @DisplayName("userId null 케이스")
  @Test
  void isValidWithUserIdNull()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setUserId(null);

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("userId empty 케이스")
  @Test
  void isValidWithUserIdEmpty()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setUserId("");

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("userId blank 케이스")
  @Test
  void isValidWithUserIdWithBlank()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setUserId(" " + dto.getUserId() + " ");

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertTrue(validWithTrim);
  }

  @DisplayName("password null 케이스")
  @Test
  void isValidWithPasswordNull()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setPassword(null);

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("password empty 케이스")
  @Test
  void isValidWithPasswordEmpty()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setPassword("");

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("password blank 케이스")
  @Test
  void isValidWithPasswordBlank()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setPassword(" " + dto.getPassword() + " ");

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertTrue(valid);
    Assertions.assertTrue(validWithTrim);
  }

  @DisplayName("password length overflow 케이스")
  @Test
  void isValidWithPasswordLengthOverflow()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setPassword("asdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklq" +
      "asdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklq" +
      "asdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklq" +
      "asdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklq" +
      "asdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqasdfghjklqa");

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("password length underflow 케이스")
  @Test
  void isValidWithPasswordLengthUnderflow()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setPassword("abcde");

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("email null 케이스")
  @Test
  void isValidWithEmailNull()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setEmail(null);

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("email empty 케이스")
  @Test
  void isValidWithEmailEmpty()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setEmail("");

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("email blank 케이스")
  @Test
  void isValidWithEmailBlank()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setEmail(" " + dto.getEmail() + " ");

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertTrue(validWithTrim);
  }

  @DisplayName("name null 케이스")
  @Test
  void isValidWithNameNull()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setName(null);

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("name empty 케이스")
  @Test
  void isValidWithNameEmpty()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setName("");

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("name length overflow 케이스")
  @Test
  void isValidWithNameLengthOverflow()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();
    dto.setName("asbcefghijk");

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertFalse(valid);
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("정상 케이스")
  @Test
  void isValid()
  {
    // Given
    RequestSignUpDTO dto = getDefaultDTO();

    // When
    boolean valid         = signUpValidator.isValid(dto);
    boolean validWithTrim = signUpValidator.isValidWithTrim(dto);

    // Then
    Assertions.assertTrue(valid);
    Assertions.assertTrue(validWithTrim);
  }

  private RequestSignUpDTO getDefaultDTO()
  {
    RequestSignUpDTO dto = new RequestSignUpDTO();
    dto.setUserId("ez2archive");
    dto.setEmail("ez2archive1@gmail.com");
    dto.setName("ezu");
    dto.setPassword("duck duck duck goose!");

    return dto;
  }
}