package com.ez2archive.vo;

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

  /**
   * 2022.08.24 이미지 파일 업로드/다운로드 없이 프론트-엔드에서 이미지를 정적으로 처리하기로 결정.
   */
  //private String fileUUID;

  public AchieveVO(){}

  public AchieveVO(Long recordId, Long musicInfoId, String name, int score, Double percentage, int rank, String difficulty, String grade, boolean isAllCool, boolean isNoMiss)
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
  }

  /**
   * QLRM 전용 생성자 (H2)
   */
  public AchieveVO(BigInteger recordId, BigInteger musicInfoId, String name, int score, Double percentage, int rank, String difficulty, String grade, boolean isAllCool, boolean isNoMiss)
  {
    this(recordId.longValue(), musicInfoId.longValue(), name, score, percentage, rank, difficulty, grade, isAllCool, isNoMiss);
  }

  /**
   * QLRM 전용 생성자 (MySQL)
   */
  public AchieveVO(BigInteger recordId, BigInteger musicInfoId, String name, BigInteger score, Float percentage, int rank, String difficulty, String grade, BigDecimal isAllCool, BigDecimal isNoMiss)
  {
    this(recordId.longValue(), musicInfoId.longValue(), name, score.intValue(), percentage.doubleValue(), rank, difficulty, grade, isAllCool.intValue() > 0, isNoMiss.intValue() > 0);
  }

}
