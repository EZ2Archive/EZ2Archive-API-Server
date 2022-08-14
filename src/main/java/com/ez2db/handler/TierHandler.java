package com.ez2db.handler;

import com.ez2db.entity.KeyType;

public interface TierHandler
{
  double getPoint(KeyType keyType, int bestScore, int level, int score);
}
