package com.ez2archive.dto.achieve;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@SuppressWarnings("unused")
@Getter @Setter
public class AchieveDTO
{
  private Long recordId;

  private Long musicInfoId;

  private String name;

  private int score;

  private double point;


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

  public AchieveDTO(){}

  public AchieveDTO(Long recordId, Long musicInfoId, String name, int score, double point, double percentage, int rank, String difficulty, String grade, boolean isAllCool, boolean isNoMiss)
  {
    this.recordId = recordId;
    this.musicInfoId = musicInfoId;
    this.name = name;
    this.score = score;
    this.point = point;
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
  public AchieveDTO(BigInteger recordId, BigInteger musicInfoId, String name, int score, Double point, Double percentage, int rank, String difficulty, String grade, boolean isAllCool, boolean isNoMiss)
  {
    this(recordId.longValue(), musicInfoId.longValue(), name, score, point, percentage, rank, difficulty, grade, isAllCool, isNoMiss);
  }

  /**
   * QLRM 전용 생성자 (MySQL)
   */
  public AchieveDTO(BigInteger recordId, BigInteger musicInfoId, String name, BigInteger score, Double point, Float percentage, int rank, String difficulty, String grade, BigDecimal isAllCool, BigDecimal isNoMiss)
  {
    this(recordId.longValue(), musicInfoId.longValue(), name, score.intValue(), point, percentage.doubleValue(), rank, difficulty, grade, isAllCool.intValue() > 0, isNoMiss.intValue() > 0);
  }

}
