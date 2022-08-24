package com.ez2archive.common.validator;

import com.ez2archive.entity.Member;
import com.ez2archive.entity.MemberAuthority;

import java.util.regex.Pattern;

public class DefaultMemberValidator implements Validator<Member>
{
  private static final Pattern ID_REG_EXP = Pattern.compile("^[A-za-z0-9]{4,12}$");

  @Override
  public boolean isValid(Member member)
  {
    return isIdValid(member.getUserId())
      && isPwdValid(member.getPassword())
      && isNameValid(member.getName())
      && isAuthorityValid(member.getAuthority());
  }

  private boolean isIdValid(String userId)
  {
    return ID_REG_EXP.matcher( userId ).matches();
  }

  private boolean isPwdValid(String password)
  {
    return password.length() > 5
      && password.length() <= 500;
  }

  private boolean isNameValid(String name)
  {
    return name.length() > 0
      && name.length() <= 10;
  }

  private boolean isAuthorityValid(MemberAuthority authority)
  {
    return authority != null;
  }
}
