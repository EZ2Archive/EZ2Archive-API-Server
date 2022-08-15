package com.ez2db.handler;

import com.ez2db.common.exception.business.IllegalValueException;
import com.ez2db.entity.KeyType;
import com.ez2db.entity.TierGrade;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class DefaultTierHandler implements TierHandler<TierGrade>
{

  private static final Map<KeyType, Map<Integer, Integer>> POINT_MAP = new HashMap<>();

  static
  {
    final Map<Integer, Integer> fourMap = new HashMap<>();
    fourMap.put(20, 300);
    fourMap.put(19, 300);
    fourMap.put(18, 260);
    fourMap.put(17, 225);
    fourMap.put(16, 200);
    fourMap.put(15, 180);
    fourMap.put(14, 160);
    fourMap.put(13, 140);
    fourMap.put(12, 130);
    fourMap.put(11, 120);
    fourMap.put(10, 110);
    fourMap.put( 9, 100);
    fourMap.put( 8,  90);
    fourMap.put( 7,  80);
    fourMap.put( 6,  70);
    fourMap.put( 5,  60);
    fourMap.put( 4,  50);
    fourMap.put( 3,  40);
    fourMap.put( 2,  30);
    fourMap.put( 1,  20);
    POINT_MAP.put(KeyType.FOUR, fourMap);

    for ( KeyType keyType : KeyType.values() )
    {
      if( keyType == KeyType.FOUR) continue;

      final Map<Integer, Integer> map = new HashMap<>();
      map.put(20, 300);
      map.put(19, 260);
      map.put(18, 225);
      map.put(17, 200);
      map.put(16, 180);
      map.put(15, 160);
      map.put(14, 140);
      map.put(13, 130);
      map.put(12, 120);
      map.put(11, 110);
      map.put(10, 100);
      map.put( 9,  90);
      map.put( 8,  80);
      map.put( 7,  70);
      map.put( 6,  60);
      map.put( 5,  50);
      map.put( 4,  40);
      map.put( 3,  30);
      map.put( 2,  20);
      map.put( 1,  10);

      POINT_MAP.put(keyType, map);
    }
  }

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
  public double getPoint(KeyType keyType, int bestScore, int level, int score)
  {
    int maxPoint = POINT_MAP.get(keyType).get(level);

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


}
