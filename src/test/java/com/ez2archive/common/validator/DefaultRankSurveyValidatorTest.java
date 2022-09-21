package com.ez2archive.common.validator;

import com.ez2archive.entity.Member;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.entity.RankSurvey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DefaultRankSurveyValidatorTest
{

  private Validator<RankSurvey> rankSurveyValidator;

  @BeforeEach
  void init()
  {
    rankSurveyValidator = new DefaultRankSurveyValidator();
  }

  @DisplayName("성공 케이스")
  @Test
  public void isValid()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(MusicInfo.builder()
        .id(1L)
        .build())
      .member(Member.builder()
        .id(1L)
        .build())
      .point(0)
      .addTime(LocalDateTime.now())
      .lastModifyTime(LocalDateTime.now())
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertTrue(validWithTrim);
  }

  @DisplayName("musicInfo null 케이스")
  @Test
  public void isValidWithMusicInfoNull()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(null)
      .member(Member.builder()
        .id(1L)
        .build())
      .point(0)
      .addTime(LocalDateTime.now())
      .lastModifyTime(LocalDateTime.now())
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("musicInfoId null 케이스")
  @Test
  public void isValidWithMusicInfoIdNull()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(MusicInfo.builder()
        .id(null)
        .build())
      .member(Member.builder()
        .id(1L)
        .build())
      .point(0)
      .addTime(LocalDateTime.now())
      .lastModifyTime(LocalDateTime.now())
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("musicInfoId underflow 케이스")
  @Test
  public void isValidWithMusicInfoIdUnderflow()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(MusicInfo.builder()
        .id(-1L)
        .build())
      .member(Member.builder()
        .id(1L)
        .build())
      .point(0)
      .addTime(LocalDateTime.now())
      .lastModifyTime(LocalDateTime.now())
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("member null 케이스")
  @Test
  public void isValidWithMemberNull()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(MusicInfo.builder()
        .id(1L)
        .build())
      .member(null)
      .point(0)
      .addTime(LocalDateTime.now())
      .lastModifyTime(LocalDateTime.now())
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("memberId null 케이스")
  @Test
  public void isValidWithMemberIdNull()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(MusicInfo.builder()
        .id(1L)
        .build())
      .member(Member.builder()
        .id(null)
        .build())
      .point(0)
      .addTime(LocalDateTime.now())
      .lastModifyTime(LocalDateTime.now())
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("memberId underflow 케이스")
  @Test
  public void isValidWithMemberIdUnderflow()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(MusicInfo.builder()
        .id(1L)
        .build())
      .member(Member.builder()
        .id(-1L)
        .build())
      .point(0)
      .addTime(LocalDateTime.now())
      .lastModifyTime(LocalDateTime.now())
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("point underflow 케이스")
  @Test
  public void isValidWithPointUnderflow()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(MusicInfo.builder()
        .id(1L)
        .build())
      .member(Member.builder()
        .id(1L)
        .build())
      .point(-3)
      .addTime(LocalDateTime.now())
      .lastModifyTime(LocalDateTime.now())
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("point overflow 케이스")
  @Test
  public void isValidWithPointOverflow()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(MusicInfo.builder()
        .id(1L)
        .build())
      .member(Member.builder()
        .id(1L)
        .build())
      .point(3)
      .addTime(LocalDateTime.now())
      .lastModifyTime(LocalDateTime.now())
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("addTime null 케이스")
  @Test
  public void isValidWithAddTimeNull()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(MusicInfo.builder()
        .id(1L)
        .build())
      .member(Member.builder()
        .id(1L)
        .build())
      .point(0)
      .addTime(null)
      .lastModifyTime(LocalDateTime.now())
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("lastModifyTime null 케이스")
  @Test
  public void isValidWithLastModifyTimeNull()
  {
    // given
    final RankSurvey rankSurvey = RankSurvey.builder()
      .music(MusicInfo.builder()
        .id(1L)
        .build())
      .member(Member.builder()
        .id(1L)
        .build())
      .point(0)
      .addTime(LocalDateTime.now())
      .lastModifyTime(null)
      .build();

    // when
    final boolean validWithTrim = rankSurveyValidator.isValidWithTrim(rankSurvey);

    // then
    Assertions.assertFalse(validWithTrim);
  }
}