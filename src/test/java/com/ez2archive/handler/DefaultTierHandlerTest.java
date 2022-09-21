package com.ez2archive.handler;

import com.ez2archive.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DefaultTierHandlerTest
{
  private TierHandler<TierGrade> tierHandler;

  @BeforeEach
  void init()
  {
    tierHandler = new DefaultTierHandler();
  }

  @DisplayName("tierPoint 계산 케이스")
  @Test
  void getPointAsScore()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("PUPA")
      .artist("Morimori Atsushi")
      .keyType(KeyType.FOUR)
      .bestScore(1_101_781)
      .totalNote(1_781)
      .category("2021")
      .difficulty(MusicDifficulty.SHD)
      .level(19)
      .addTime(LocalDateTime.now())
      .build();
    final int score = 1_100_432;

    // When
    final double expected = 299.013d;
    final double actual = tierHandler.getTierPointAsScore(musicInfo.getKeyType(), musicInfo.getBestScore(), musicInfo.getLevel(), score);

    // Then
    Assertions.assertEquals(expected, actual);
  }

  @DisplayName("changePoint 계산 케이스")
  @Test
  void getChangePoint()
  {
    // Given
    final Member member = Member.builder().build();
    final KeyType keyType = KeyType.FOUR;

    final double totalPoint = 12016.460999999998d;


    // When
    final double expected = 9517.0d;
    final double actual = tierHandler.getChangePoint(keyType, totalPoint);

    // Then
    Assertions.assertEquals(expected, actual);
  }

  @DisplayName("currentGrade 계산 케이스")
  @Test
  void getCurrentGrade()
  {
    // Given
    final double changePoint = 9517.0d;

    // When
    final TierGrade expected = TierGrade.NEMESIS2;
    final TierGrade actual = tierHandler.getCurrentGrade(changePoint);

    // Then
    Assertions.assertEquals(expected, actual);
  }

  @DisplayName("다음 티어까지 필요 포인트 계산 케이스")
  @Test
  void getPointUntilNextTier()
  {
    // Given
    final double changePoint = 9517.0d;

    // When
    final double expected = 83.0d;
    final double actual = tierHandler.getPointUntilNextTier(changePoint);

    // Then
    Assertions.assertEquals(expected, actual);
  }

  @DisplayName("포인트 점수 조회 케이스")
  @Test
  void getPoint()
  {
    // Given
    final KeyType keyType = KeyType.FOUR;
    final int level = 19;

    // When
    int expected = 300;
    int actual = tierHandler.getPoint(keyType, level);

    // Then
    Assertions.assertEquals(expected, actual);
  }
}