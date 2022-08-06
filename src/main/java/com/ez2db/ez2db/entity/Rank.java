package com.ez2db.ez2db.entity;

/**
 * 랭크 점수
 */
public enum Rank
{
  SPPP(1_090_000),   // 1,090,000
  SPP(1_050_000),    // 1,050,000
  SP(1_000_000),     // 1,000,000
  AP(950_000),       // 950,000
  A(900_000),        // 900,000
  B(850_000),        // 850,000
  C(750_000),        // 750,000
  D(650_000),        // 650,000
  F(550_000);        // 550,000

  /** 랭크 점수 */
  private final int score;

  Rank(int score)
  {
    this.score = score;
  }

  public int getScore()
  {
    return score;
  }
}
