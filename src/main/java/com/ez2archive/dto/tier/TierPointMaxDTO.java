package com.ez2archive.dto.tier;

import lombok.Data;

@Data
public class TierPointMaxDTO
{
  private Long memberId;
  private Long musicInfoId;
  private double maxPoint;

  public TierPointMaxDTO()
  {
  }

  public TierPointMaxDTO(Long memberId, Long musicInfoId, double maxPoint)
  {
    this.memberId = memberId;
    this.musicInfoId = musicInfoId;
    this.maxPoint = maxPoint;
  }
}
