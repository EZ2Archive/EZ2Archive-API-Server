package com.ez2archive.handler;

import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.TierGrade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultTierPointHandlerTest
{
  private TierHandler<TierGrade> tierHandler;

  @BeforeEach
  void init()
  {
    tierHandler = new DefaultTierHandler();
  }

  @DisplayName("티어 점수 계산")
  @Test
  void getPointAsScore()
  {
    // Given
    final KeyType keyType = KeyType.FIVE;
    final int bestScore = 1_100_000;
    final int level = 19;
    final int score = 1_090_000;

    double expected = 253.659d;

    // When
    double actual = tierHandler.getPointAsScore(keyType, bestScore, level, score);

    // Then
    Assertions.assertEquals(expected, actual);
  }
}