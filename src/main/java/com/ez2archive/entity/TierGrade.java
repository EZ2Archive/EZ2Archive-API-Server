package com.ez2archive.entity;

import java.util.ArrayList;
import java.util.List;

public enum TierGrade
{
  BEGINNER2(0), BEGINNER1(400),
  BRONZE4(800), BRONZE3(1_050), BRONZE2(1_300), BRONZE1(1_550),
  SILVER4(2_000), SILVER3(2_400), SILVER2(2_800), SILVER1(3_200),
  GOLD4(3_800), GOLD3(4_300), GOLD2(4_800), GOLD1(5_200),
  PLATINUM4(6_000), PLATINUM3(6_500), PLATINUM2(7_000), PLATINUM1(7_500),
  DIAMOND4(7_750), DIAMOND3(7_950), DIAMOND2(8_150), DIAMOND1(8_350),
  MASTER4(8_550), MASTER3(8_700), MASTER2(8_850), MASTER1(9_000),
  NEMESIS4(9_150), NEMESIS3(9_300), NEMESIS2(9_450), NEMESIS1(9_600),
  VANQUISHER2(9_700), VANQUISHER1(9_800),
  DOMINATOR(9_850),
  THE_ABSOLUTE(9_920);
  
  private final int score;
  
  TierGrade(int score)
  {
    this.score = score;
  }

  public int score()
  {
    return this.score;
  }

  /**
   * <pre>
   *   인자로 받은 티어등급의 그룹을 반환한다.
   *   예를 들어, TierGrade.SILVER2를 인자로 받을 경우 { TierGrade.SLIVER4, TierGrade.SLIVER3, TierGrade.SLIVER2, TierGrade.SLIVER1 } 를 반환한다.
   * </pre>
   *
   * <pre>
   * {@code
   *   List<TierGrade> tierGradeList = TierGrade.ofGroup(TierGrade.SILVER2);
   *   // tierGradeList = { TierGrade.SLIVER4, TierGrade.SLIVER3, TierGrade.SLIVER2, TierGrade.SLIVER1 }
   * }
   * </pre>
   */
  public static List<TierGrade> ofGroup(TierGrade tierGrade)
  {
    List<TierGrade> resultList = new ArrayList<>();

    String tierStr = tierGrade.name().substring(0, tierGrade.name().length()-1);

    for ( TierGrade grade : TierGrade.values() )
    {
      if( grade.name().contains(tierStr) )
        resultList.add(grade);
    }

    return resultList;
  }
}
