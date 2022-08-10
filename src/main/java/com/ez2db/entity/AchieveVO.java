package com.ez2db.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@SuppressWarnings("unused")
@Getter @Setter
public class AchieveVO
{
  private Long recordId;

  private Long musicInfoId;

  private String name;

  private int score;

  private int rank;

  private String grade;

  private boolean isAllCool;

  private boolean isNoMiss;

  private String imagePath;

  public AchieveVO(){}

  public AchieveVO(Long recordId, Long musicInfoId, String name, int score, int rank, String grade, boolean isAllCool, boolean isNoMiss, String imagePath)
  {
    this.recordId = recordId;
    this.musicInfoId = musicInfoId;
    this.name = name;
    this.score = score;
    this.rank = rank;
    this.grade = grade;
    this.isAllCool = isAllCool;
    this.isNoMiss = isNoMiss;
    this.imagePath = imagePath;
  }

  /**
   * QLRM 전용 생성자
   */
  public AchieveVO(BigInteger recordId, BigInteger musicInfoId, String name, int score, int rank, String grade, boolean isAllCool, boolean isNoMiss, String imagePath)
  {
    this(recordId.longValue(), musicInfoId.longValue(), name, score, rank, grade, isAllCool, isNoMiss, imagePath);
  }
}
