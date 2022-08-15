package com.ez2db.handler;

import com.ez2db.entity.KeyType;

public interface TierHandler<T>
{
  double getPoint(KeyType keyType, int bestScore, int level, int score);

  double getChangePoint(KeyType keyType, double totalPoint);

  T getCurrentGrade(double changePoint);

  double getPointUntilNextTier(double changePoint);

}
