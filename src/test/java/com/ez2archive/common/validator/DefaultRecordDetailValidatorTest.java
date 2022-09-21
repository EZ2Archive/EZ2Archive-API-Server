package com.ez2archive.common.validator;

import com.ez2archive.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DefaultRecordDetailValidatorTest
{
  private Validator<RecordDetail> recordDetailValidator;

  @BeforeEach
  void init()
  {
    recordDetailValidator = new DefaultRecordDetailValidator();
  }

  @DisplayName("성공 케이스")
  @Test
  public void isValid()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(1L)
      .isNoMiss(false)
      .isAllCool(false)
      .score(1_100_000)
      .point(100)
      .percentage(100)
      .grade(Grade.SPPP)
      .addTime(LocalDateTime.now())
      .record(Record.builder()
        .music(getMusicInfo())
        .build())
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
    Assertions.assertEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertTrue(validWithTrim);
  }

  @DisplayName("allCool but not noMiss 케이스")
  @Test
  public void isValidWithAllCoolAndNotNoMiss()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(1L)
      .isNoMiss(false)
      .isAllCool(true)
      .score(1_100_313)
      .point(100)
      .percentage(100)
      .grade(Grade.SPPP)
      .addTime(LocalDateTime.now())
      .record(Record.builder()
        .music(getMusicInfo())
        .build())
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
    Assertions.assertEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("musicInfoId null 케이스")
  @Test
  public void isValidWithMusicInfoIdNull()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(null)
      .isNoMiss(false)
      .isAllCool(false)
      .score(1_100_000)
      .point(100)
      .percentage(100)
      .grade(Grade.SPPP)
      .addTime(LocalDateTime.now())
      .record(Record.builder()
        .music(getMusicInfo())
        .build())
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
    Assertions.assertNotEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("score overflow 케이스")
  @Test
  public void isValidWithScoreOverflow()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(1L)
      .isNoMiss(false)
      .isAllCool(false)
      .score(1_100_313)
      .point(100)
      .percentage(100)
      .grade(Grade.SPPP)
      .addTime(LocalDateTime.now())
      .record(Record.builder()
        .music(getMusicInfo())
        .build())
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
    Assertions.assertEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("score underflow 케이스")
  @Test
  public void isValidWithScoreUnderflow()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(1L)
      .isNoMiss(false)
      .isAllCool(false)
      .score(Grade.F.score() - 1)
      .point(100)
      .percentage(100)
      .grade(Grade.SPPP)
      .addTime(LocalDateTime.now())
      .record(Record.builder()
        .music(getMusicInfo())
        .build())
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
    Assertions.assertEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("percentage overflow 케이스")
  @Test
  public void isValidWithPercentageOverflow()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(1L)
      .isNoMiss(false)
      .isAllCool(false)
      .score(1_100_000)
      .point(100)
      .percentage(100.1f)
      .grade(Grade.SPPP)
      .addTime(LocalDateTime.now())
      .record(Record.builder()
        .music(getMusicInfo())
        .build())
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
    Assertions.assertEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("percentage underflow 케이스")
  @Test
  public void isValidWithPercentageUnderflow()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(1L)
      .isNoMiss(false)
      .isAllCool(false)
      .score(1_100_313)
      .point(100)
      .percentage(49.9f)
      .grade(Grade.SPPP)
      .addTime(LocalDateTime.now())
      .record(Record.builder()
        .music(getMusicInfo())
        .build())
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
    Assertions.assertEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("grade null 케이스")
  @Test
  public void isValidWithGradeNull()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(1L)
      .isNoMiss(false)
      .isAllCool(false)
      .score(1_100_313)
      .point(100)
      .percentage(100f)
      .grade(null)
      .addTime(LocalDateTime.now())
      .record(Record.builder()
        .music(getMusicInfo())
        .build())
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
    Assertions.assertEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("addTime null 케이스")
  @Test
  public void isValidWithAddTimeNull()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(1L)
      .isNoMiss(false)
      .isAllCool(false)
      .score(1_100_313)
      .point(100)
      .percentage(100f)
      .grade(Grade.SPPP)
      .addTime(null)
      .record(Record.builder()
        .music(getMusicInfo())
        .build())
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
    Assertions.assertEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("record null 케이스")
  @Test
  public void isValidWithRecordNull()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(1L)
      .isNoMiss(false)
      .isAllCool(false)
      .score(1_100_313)
      .point(100)
      .percentage(100f)
      .grade(Grade.SPPP)
      .addTime(LocalDateTime.now())
      .record(null)
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
//    Assertions.assertEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("recordId null 케이스")
  @Test
  public void isValidWithMusicInfoNull()
  {
    // given
    final RecordDetail recordDetail = RecordDetail.builder()
      .musicInfoId(1L)
      .isNoMiss(false)
      .isAllCool(false)
      .score(1_100_313)
      .point(100)
      .percentage(100f)
      .grade(Grade.SPPP)
      .addTime(LocalDateTime.now())
      .record(Record.builder()
        .music(getMusicInfo())
        .build())
      .build();

    // when
    boolean validWithTrim = recordDetailValidator.isValidWithTrim(recordDetail);

    // then
    Assertions.assertEquals(recordDetail.getRecord().getMusic().getId(), recordDetail.getMusicInfoId());
    Assertions.assertFalse(validWithTrim);
  }

  private MusicInfo getMusicInfo()
  {
    return MusicInfo.builder()
      .id(1L)
      .name("musicName")
      .artist("artistName")
      .keyType(KeyType.FOUR)
      .difficulty(MusicDifficulty.EZ)
      .category("1ST")
      .level(3)
      .rank(0)
      .bestScore(1100312)
      .totalNote(312)
      .addTime(LocalDateTime.now())
      .build();
  }
}