package com.ez2archive.dto.achieve;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AchieveDetailDTO
{
  private Long musicInfoId;
  private String name;
  private Double avgScore;
  private Double avgTierPoint;

  public AchieveDetailDTO()
  {
  }

  @Builder
  public AchieveDetailDTO(Long musicInfoId, String name, Double avgScore, Double avgTierPoint)
  {
    this.musicInfoId = musicInfoId;
    this.name = name;
    this.avgScore = avgScore;
    this.avgTierPoint = avgTierPoint;
  }

}
