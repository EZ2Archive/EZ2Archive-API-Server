package com.ez2db.common.validator;

import com.ez2db.entity.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultMemberValidatorTest
{
  private DefaultMemberValidator validator;

  @BeforeEach
  void setUp()
  {
    validator = new DefaultMemberValidator();
  }

  @AfterEach
  void tearDown()
  {
  }

  @Test
  public void isValidate() throws Exception
  {
    // given
    final String userId = "ksh1042";
    final String password = "ksh1042";
    final Member member = new Member();
    member.setUserId(userId);
    member.setPassword(password);

    // when
    boolean actual = validator.isValidate(member);

    // then
    Assertions.assertTrue(actual);
  }
}