package com.ez2archive.dto.tier;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class TierAverageDTO
{
  private Long musicInfoId;
  private String name;
  private double averageScore;
  private double averagePoint;

  public TierAverageDTO()
  {
  }

  @Builder
  public TierAverageDTO(Long musicInfoId, String name, double averageScore, double averagePoint)
  {
    this.musicInfoId  = musicInfoId;
    this.name         = name;
    this.averageScore = averageScore;
    this.averagePoint = averagePoint;
  }

  /** QLRM MySQL Generator */
  public TierAverageDTO(BigInteger musicInfoId, String name, BigDecimal averageScore, Double averagePoint)
  {
    this(musicInfoId.longValue(), name, averageScore.doubleValue(), averagePoint);
  }
}
