package com.ez2archive.handler;

import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.TierGrade;
import com.ez2archive.repository.MusicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.round;

@RequiredArgsConstructor
@Component
public class DefaultTierHandler implements TierHandler<TierGrade>
{
  private final MusicInfoRepository musicInfoRepository;

  /**
   * 레벨별 포인트 가중치
   */
  private final Map<Integer, Integer> pointMap = Map.ofEntries(
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
  );


  @Override
  public double getTierPointAsScore(KeyType keyType, int bestScore, int level, int score)
  {
    return round((pow(( 100d * ((double)score / bestScore) - 55d ), 1.209765825d) / 100 * pointMap.get(level)) * 1000) / 1000d;
  }


  @Override
  public double getChangePoint(KeyType keyType, double totalPoint)
  {
    // 
    final double exchangeConstant = musicInfoRepository.findMusicInfosByKeyTypeOrderByLevelDesc(keyType, Pageable.ofSize(50))
      .stream()
      .map(musicInfo -> pointMap.get(musicInfo.getLevel()))
      .reduce(Integer::sum)
      .orElseThrow(() -> new IllegalStateException("음원 포읹트 정보가 존재하지 않습니다.")) / 10_000d;

    final double result = totalPoint / exchangeConstant;

    return round(result * 1000d) / 1000d;
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
