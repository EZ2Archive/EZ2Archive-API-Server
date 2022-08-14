package com.ez2db.handler;

import com.ez2db.entity.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultTierHandlerTest
{
  private TierHandler tierHandler;

  @BeforeEach
  void init()
  {
    tierHandler = new DefaultTierHandler();
  }

  @DisplayName("티어 점수 계산")
  @Test
  void getPoint()
  {
    // Given
    KeyType keyType = KeyType.FIVE;
    int bestScore = 1_100_000;
    int level = 19;
    int score = 1_090_000;

    double expected = 253.659d;

    // When
    double actual = tierHandler.getPoint(keyType, bestScore, level, score);

    // Then
    Assertions.assertEquals(expected, actual);
  }
}