package com.ez2archive.handler;

import com.ez2archive.entity.*;
import com.ez2archive.repository.MusicInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
class DefaultTierHandlerTest
{
  @Mock
  private MusicInfoRepository musicInfoRepository;

  @InjectMocks
  private DefaultTierHandler tierHandler;

  @BeforeEach
  void init()
  {
    tierHandler = new DefaultTierHandler(musicInfoRepository);
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
    final double expected = 259.144d;
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

    final double totalPoint = 9891d;

    // When
    List<MusicInfo> musicInfoList = IntStream.range(0,9).mapToObj(value -> MusicInfo.builder().level(19).build()).collect(Collectors.toList());
    musicInfoList.addAll( IntStream.range(0,18).mapToObj(value -> MusicInfo.builder().level(18).build()).collect(Collectors.toList()) );
    musicInfoList.addAll( IntStream.range(0,23).mapToObj(value -> MusicInfo.builder().level(17).build()).collect(Collectors.toList()) );
    Mockito.when(musicInfoRepository.findMusicInfosByKeyTypeOrderByLevelDesc(Mockito.any(), Mockito.any()))
      .thenReturn(musicInfoList);

    final double expected = 9000;
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
}