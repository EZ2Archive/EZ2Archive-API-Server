package com.ez2archive.handler;

import com.ez2archive.entity.KeyType;

public interface TierHandler<T extends Enum<T>>
{
  /**
   * <pre>
   *   키 타입, 최고 가용 점수, 음원 레벨, 사용자의 득점 점수를 통해 티어포인트를 반환한다.
   *   득점 점수 포인트 환산식 : (100 * (score / bestScore) - 55) ^ 1.209765825 / maxPoint / 100
   * </pre>
   * @param keyType 키 타입(4Key, 5Key, ...)
   * @param bestScore 해당 음원에서 가능한 최고점수
   * @param level 해당 음원의 레벨
   * @param score 사용자 득점 점수
   * @return 티어포인트를 반환한다.
   */
  double getTierPointAsScore(KeyType keyType, int bestScore, int level, int score);

  /**
   * <pre>
   *   현재의 환산상수와 입력받은 포인트 총합을 통해 환산포인트를 반환한다.
   *   ※ 환산상수(exchangeConstant) : 키 타입으로 구분된 음원들을 레벨 역순으로 정렬하여 상위 50곡의 포인트를 전부 합한 뒤 10,000으로 나눈 값
   * </pre>
   * @param keyType 키 타입
   * @param totalPoint 레벨 기준 상위 50곡의 플레이 기록 티어 포인트 총합
   * @return 환산포인트를 반환한다.
   */
  double getChangePoint(KeyType keyType, double totalPoint);

  /**
   * <pre>
   *   입력받은 환산포인트를 통해 현재의 티어 등급을 반환한다.
   * </pre>
   * @param changePoint 환산포인트
   * @see com.ez2archive.handler.TierHandler#getChangePoint(KeyType, double);
   * @return
   */
  T getCurrentGrade(double changePoint);

  /**
   * <pre>
   *   입력받은 환산포인트를 통해 다음 티어까지의 남은 포인트를 반환한다.
   * </pre>
   * @param changePoint 환산포인트
   * @see com.ez2archive.handler.TierHandler#getChangePoint(KeyType, double);
   * @return
   */
  double getPointUntilNextTier(double changePoint);

}
