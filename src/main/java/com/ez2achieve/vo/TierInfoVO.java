package com.ez2achieve.vo;

import com.ez2achieve.entity.TierGrade;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TierInfoVO
{
  private final String name;
  private final TierGrade tierGrade;
  private final double totalPoint;
  private final double changePoint;
  private final double untilNextTier;

  @Builder
  public TierInfoVO(String name, TierGrade tierGrade, double totalPoint, double changePoint, double untilNextTier)
  {
    this.name = name;
    this.tierGrade = tierGrade;
    this.totalPoint = totalPoint;
    this.changePoint = changePoint;
    this.untilNextTier = untilNextTier;
  }
}
