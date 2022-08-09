package com.ez2db.common.validator;

import com.ez2db.entity.Member;
import com.ez2db.entity.MemberAuthority;

import java.util.regex.Pattern;

public class DefaultMemberValidator implements Validator<Member>
{
  private static final Pattern ID_REG_EXP = Pattern.compile("^[A-za-z0-9]{4,12}$");

  @Override
  public boolean isValidate(Member member)
  {
    return isIdValidate(member.getUserId())
      && isPwdValidate(member.getPassword())
      && isNameValidate(member.getName())
      && isAuthorityValidate(member.getAuthority());
  }

  private boolean isIdValidate(String userId)
  {
    return ID_REG_EXP.matcher( userId ).matches();
  }

  private boolean isPwdValidate(String password)
  {
    return password.length() > 5
      && password.length() <= 500;
  }

  private boolean isNameValidate(String name)
  {
    return name.length() > 0
      && name.length() <= 10;
  }

  private boolean isAuthorityValidate(MemberAuthority authority)
  {
    return authority != null;
  }
}
