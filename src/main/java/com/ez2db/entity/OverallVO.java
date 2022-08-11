package com.ez2db.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@SuppressWarnings("unused")
@Getter @Setter
public class OverallVO
{
  /** 정확도 평균값 */
  private double rateAvg;
  
  /** 음원 총 갯수 */
  private long totalCnt;

  /** All Cool 갯수 */
  private int allCoolCnt;

  /** No Miss 갯수 */
  private int noMissCnt;

  /** S+++ 갯수 */
  private int spppCnt;

  /** S++ 갯수 */
  private int sppCnt;

  /** S+ 갯수 */
  private int spCnt;

  public OverallVO(){}

  public OverallVO(double rateAvg, long totalCnt, int allCoolCnt, int noMissCnt, int spppCnt, int sppCnt, int spCnt)
  {
    this.rateAvg = rateAvg;
    this.totalCnt = totalCnt;
    this.allCoolCnt = allCoolCnt;
    this.noMissCnt = noMissCnt;
    this.spppCnt = spppCnt;
    this.sppCnt = sppCnt;
    this.spCnt = spCnt;
  }

  /**
   * QLRM 전용 Long/BigInteger 변환 생성자
   */
  public OverallVO(BigDecimal rateAvg, Integer totalCnt, BigInteger allCoolCnt, BigInteger noMissCnt, BigInteger spppCnt, BigInteger sppCnt, BigInteger spCnt)
  {
    this(rateAvg.doubleValue(), totalCnt.longValue(), allCoolCnt.intValue(), noMissCnt.intValue(), spppCnt.intValue(), sppCnt.intValue(), spCnt.intValue());
  }
}
