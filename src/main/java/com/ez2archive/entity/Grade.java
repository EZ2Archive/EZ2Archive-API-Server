package com.ez2archive.entity;

/**
 * 점수 기록 등급
 */
public enum Grade
{
  SPPP(1_090_000),   // S+++ 1,090,000
  SPP(1_050_000),    // S++  1,050,000
  SP(1_025_000),     // S+   1,025,000
  S(1_000_000),      // S+   1,000,000
  AP(950_000),       // A+   950,000
  A(900_000),        // A    900,000
  B(850_000),        // B    850,000
  C(750_000),        // C    750,000
  D(650_000),        // D    650,000
  F(550_000);        // F    550,000

  /** 등급 점수 */
  private final int score;

  Grade(int score)
  {
    this.score = score;
  }

  public int getScore()
  {
    return score;
  }
}
