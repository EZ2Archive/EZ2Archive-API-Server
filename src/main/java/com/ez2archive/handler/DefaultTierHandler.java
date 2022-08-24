package com.ez2archive.handler;

import com.ez2archive.common.exception.business.IllegalValueException;
import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.TierGrade;

import java.util.Map;

import static java.lang.Math.*;

public class DefaultTierHandler implements TierHandler<TierGrade>
{

  private final Map<KeyType, Map<Integer, Integer>> pointMap = Map.of(
    KeyType.FOUR,  Map.ofEntries(
      Map.entry(20, 300),
      Map.entry(19, 300),
      Map.entry(18, 260),
      Map.entry(17, 225),
      Map.entry(16, 200),
      Map.entry(15, 180),
      Map.entry(14, 160),
      Map.entry(13, 140),
      Map.entry(12, 130),
      Map.entry(11, 120),
      Map.entry(10, 110),
      Map.entry( 9, 100),
      Map.entry( 8,  90),
      Map.entry( 7,  80),
      Map.entry( 6,  70),
      Map.entry( 5,  60),
      Map.entry( 4,  50),
      Map.entry( 3,  40),
      Map.entry( 2,  30),
      Map.entry( 1,  20)
    ),
    KeyType.FIVE,  Map.ofEntries(
      Map.entry(20, 300),
      Map.entry(19, 260),
      Map.entry(18, 225),
      Map.entry(17, 200),
      Map.entry(16, 180),
      Map.entry(15, 160),
      Map.entry(14, 140),
      Map.entry(13, 130),
      Map.entry(12, 120),
      Map.entry(11, 110),
      Map.entry(10, 100),
      Map.entry( 9,  90),
      Map.entry( 8,  80),
      Map.entry( 7,  70),
      Map.entry( 6,  60),
      Map.entry( 5,  50),
      Map.entry( 4,  40),
      Map.entry( 3,  30),
      Map.entry( 2,  20),
      Map.entry( 1,  10)
    ),
    KeyType.SIX,   Map.ofEntries(
      Map.entry(20, 300),
      Map.entry(19, 260),
      Map.entry(18, 225),
      Map.entry(17, 200),
      Map.entry(16, 180),
      Map.entry(15, 160),
      Map.entry(14, 140),
      Map.entry(13, 130),
      Map.entry(12, 120),
      Map.entry(11, 110),
      Map.entry(10, 100),
      Map.entry( 9,  90),
      Map.entry( 8,  80),
      Map.entry( 7,  70),
      Map.entry( 6,  60),
      Map.entry( 5,  50),
      Map.entry( 4,  40),
      Map.entry( 3,  30),
      Map.entry( 2,  20),
      Map.entry( 1,  10)
    ),
    KeyType.EIGHT, Map.ofEntries(
      Map.entry(20, 300),
      Map.entry(19, 260),
      Map.entry(18, 225),
      Map.entry(17, 200),
      Map.entry(16, 180),
      Map.entry(15, 160),
      Map.entry(14, 140),
      Map.entry(13, 130),
      Map.entry(12, 120),
      Map.entry(11, 110),
      Map.entry(10, 100),
      Map.entry( 9,  90),
      Map.entry( 8,  80),
      Map.entry( 7,  70),
      Map.entry( 6,  60),
      Map.entry( 5,  50),
      Map.entry( 4,  40),
      Map.entry( 3,  30),
      Map.entry( 2,  20),
      Map.entry( 1,  10)
    )
  );;


  /**
   * <p>
   *   - 득점 점수 포인트 환산식
   *   - (100 * (score / bestScore) - 55) ^ 1.209765825 / maxPoint / 100
   * </p>
   * @param keyType 키 타입(4Key, 5Key, ...)
   * @param bestScore 해당 음원에서 가능한 최고점수
   * @param level 해당 음원의 레벨
   * @param score 사용자 득점 점수
   * @return 소수점 세 자리에서 반올림한 티어 포인트를 반환한다.
   */
  @Override
  public double getPointAsScore(KeyType keyType, int bestScore, int level, int score)
  {
    int maxPoint = pointMap.get(keyType).get(level);

    return round((pow(( 100d * ((double)score / bestScore) - 55d ), 1.209765825d) / 100 * maxPoint) * 1000) / 1000d;
  }

  @Override
  public double getChangePoint(KeyType keyType, double totalPoint)
  {
    double result;

    switch(keyType)
    {
      case FOUR:
        result = totalPoint / 1.2625d;
        break;
      case FIVE:
        result = totalPoint / 1.2965d;
        break;
      case SIX:
        result = totalPoint / 1.308d;
        break;
      case EIGHT:
        result = totalPoint / 1.3d;
        break;
      default:
        throw new IllegalValueException("잘못된 키 타입 정보입니다");
    }
    return round(result * 1000) / 1000;
  }

  @Override
  public TierGrade getCurrentGrade(double changePoint)
  {
    // TierGrade.values()[0] = TierGrade.BEGINNER2
    TierGrade result = TierGrade.values()[0];

    for ( TierGrade grade : TierGrade.values() )
    {
      if( changePoint >= (double)grade.score() )
        result = grade;
      else
        break;
    }
    return result;
  }

  @Override
  public double getPointUntilNextTier(double changePoint)
  {
    TierGrade currentTier = getCurrentGrade(changePoint);
    double nextScore;

    try
    {
      nextScore = TierGrade.values()[currentTier.ordinal() + 1].score();
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      // 최고 티어에 도달했을 경우
      nextScore = changePoint;
    }

    return round((nextScore - changePoint) * 1000d) / 1000d;
  }

  @Override
  public int getPoint(KeyType keyType, int level)
  {
    return this.pointMap.get(keyType).get(level);
  }

}
