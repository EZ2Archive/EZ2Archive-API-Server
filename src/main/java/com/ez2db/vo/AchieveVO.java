package com.ez2db.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@SuppressWarnings("unused")
@Getter @Setter
public class AchieveVO
{
  private Long recordId;

  private Long musicInfoId;

  private String name;

  private int score;

  private double percentage;

  private int rank;

  private String difficulty;

  private String grade;

  private boolean isAllCool;

  private boolean isNoMiss;

  private String imagePath;

  public AchieveVO(){}

  public AchieveVO(Long recordId, Long musicInfoId, String name, int score, Double percentage, int rank, String difficulty, String grade, boolean isAllCool, boolean isNoMiss, String imagePath)
  {
    this.recordId = recordId;
    this.musicInfoId = musicInfoId;
    this.name = name;
    this.score = score;
    this.percentage = percentage;
    this.rank = rank;
    this.difficulty = difficulty;
    this.grade = grade;
    this.isAllCool = isAllCool;
    this.isNoMiss = isNoMiss;
    this.imagePath = imagePath;
  }

  /**
   * QLRM 전용 생성자 (H2)
   */
  public AchieveVO(BigInteger recordId, BigInteger musicInfoId, String name, int score, Double percentage, int rank, String difficulty, String grade, boolean isAllCool, boolean isNoMiss, String imagePath)
  {
    this(recordId.longValue(), musicInfoId.longValue(), name, score, percentage, rank, difficulty, grade, isAllCool, isNoMiss, imagePath);
  }

  /**
   * QLRM 전용 생성자 (MySQL)
   */
  public AchieveVO(BigInteger recordId, BigInteger musicInfoId, String name, BigInteger score, Double percentage, int rank, String difficulty, String grade, BigDecimal isAllCool, BigDecimal isNoMiss, String imagePath)
  {
    this(recordId.longValue(), musicInfoId.longValue(), name, score.intValue(), percentage, rank, difficulty, grade, isAllCool.intValue() > 0, isNoMiss.intValue() > 0, imagePath);
  }
}
