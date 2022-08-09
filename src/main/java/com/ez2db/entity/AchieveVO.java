package com.ez2db.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AchieveVO
{
  private Long recordId;

  private String name;

  private int score;

  private int level;

  private int rank;

  @Enumerated(EnumType.STRING)
  private Grade grade;

  private File imageFile;

  public AchieveVO(Long recordId, String name, int score, int level, int rank, Grade grade, File imageFile)
  {
    this.recordId = recordId;
    this.name = name;
    this.score = score;
    this.level = level;
    this.rank = rank;
    this.grade = grade;
    this.imageFile = imageFile;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getScore()
  {
    return score;
  }

  public void setScore(int score)
  {
    this.score = score;
  }

  public int getLevel()
  {
    return level;
  }

  public void setLevel(int level)
  {
    this.level = level;
  }

  public int getRank()
  {
    return rank;
  }

  public void setRank(int rank)
  {
    this.rank = rank;
  }

  public Grade getGrade()
  {
    return grade;
  }

  public void setGrade(Grade grade)
  {
    this.grade = grade;
  }

  public File getImageFile()
  {
    return imageFile;
  }

  public void setImageFile(File imageFile)
  {
    this.imageFile = imageFile;
  }
}
