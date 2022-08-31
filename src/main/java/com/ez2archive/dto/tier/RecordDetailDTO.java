package com.ez2archive.dto.tier;

import com.ez2archive.entity.MusicDifficulty;
import lombok.Builder;
import lombok.Data;

@Data
public class RecordDetailDTO
{
  private Long recordDetailId;
  
  private String name;

  private MusicDifficulty difficulty;
  
  private int level;
  
  private int score;
  
  private double point;

  public RecordDetailDTO()
  {
  }

  @Builder
  public RecordDetailDTO(Long recordDetailId, String name, int level, MusicDifficulty difficulty, int score, double point)
  {
    this.recordDetailId = recordDetailId;
    this.name = name;
    this.difficulty = difficulty;
    this.level = level;
    this.score = score;
    this.point = point;
  }
}
