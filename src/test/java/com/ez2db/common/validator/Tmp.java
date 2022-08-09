package com.ez2db.common.validator;

import java.util.regex.Pattern;

public class Tmp
{
  private static final Pattern ID_REG_EXP = Pattern.compile("/^[A-za-z0-9]{4,12}$/");

  public static void main(String[] args)
  {
    String userId  = "ksh1042";

    System.out.println("ID_REG_EXP.matcher( userId ).matches() " + ID_REG_EXP.matcher( userId ).matches());
  }
}
