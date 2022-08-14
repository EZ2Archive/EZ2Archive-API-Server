package com.ez2db.entity;

public enum TierGrade
{
  BEGINNER2(0), BEGINNER1(500),
  BRONZE4(1_000), BRONZE3(1_500), BRONZE2(2_000), BRONZE1(2_500),
  SILVER4(3_000), SILVER3(3_500), SILVER2(4_000), SILVER1(4_500),
  GOLD4(5_000), GOLD3(5_500), GOLD2(6_000), GOLD1(6_500),
  PLATINUM4(6_750), PLATINUM3(7_000), PLATINUM2(7_250), PLATINUM1(7_500),
  DIAMOND4(7_750), DIAMOND3(7_950), DIAMOND2(8_150), DIAMOND1(8_350),
  MASTER4(8_550), MASTER3(8_700), MASTER2(8_800), MASTER1(8_900),
  NEMESIS4(9_000), NEMESIS3(9_200), NEMESIS2(9_400), NEMESIS1(9_600),
  VANQUISHER(9_700);
  
  private final int score;
  
  TierGrade(int score)
  {
    this.score = score;
  }

  public int score()
  {
    return this.score;
  }
}
