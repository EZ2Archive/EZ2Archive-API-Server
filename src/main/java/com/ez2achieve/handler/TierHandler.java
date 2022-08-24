package com.ez2achieve.handler;

import com.ez2achieve.entity.KeyType;

public interface TierHandler<T>
{
  double getPointAsScore(KeyType keyType, int bestScore, int level, int score);

  double getChangePoint(KeyType keyType, double totalPoint);

  T getCurrentGrade(double changePoint);

  double getPointUntilNextTier(double changePoint);

  int getPoint(KeyType keyType, int level);

}
