package com.ez2archive.dto.tier;

import com.ez2archive.entity.Member;
import com.ez2archive.entity.Tier;
import com.ez2archive.entity.TierGrade;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TierInfoDTO
{
  private final String name;
  private final TierGrade tierGrade;
  private final double totalPoint;
  private final double changePoint;
  private final double untilNextTier;

  @Builder
  public TierInfoDTO(String name, TierGrade tierGrade, double totalPoint, double changePoint, double untilNextTier)
  {
    this.name = name;
    this.tierGrade = tierGrade;
    this.totalPoint = totalPoint;
    this.changePoint = changePoint;
    this.untilNextTier = untilNextTier;
  }

  public static TierInfoDTO of(Tier tier, Member member)
  {
    return TierInfoDTO.builder()
      .name( member.getName() )
      .tierGrade( tier.getTierGrade() )
      .totalPoint( tier.getTotalPoint() )
      .changePoint( tier.getChangePoint() )
      .untilNextTier( tier.getUntilNextTier() )
      .build();
  }
}
