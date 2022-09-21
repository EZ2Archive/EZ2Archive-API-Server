package com.ez2archive.common.validator;

import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.MusicDifficulty;
import com.ez2archive.entity.MusicInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DefaultMusicInfoValidatorTest
{
  private Validator<MusicInfo> musicInfoValidator;

  @BeforeEach
  void init()
  {
    musicInfoValidator = new DefaultMusicInfoValidator();
  }

  @DisplayName("addTime null 케이스")
  @Test
  void isValidWithAddTimeNull()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist("artistName")
      .keyType(KeyType.FOUR)
      .difficulty(MusicDifficulty.EZ)
      .category("1ST")
      .level(3)
      .rank(0)
      .bestScore(1_100_000)
      .totalNote(0)
      .addTime(null)
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("totalNote underflow 케이스")
  @Test
  void isValidWithTotalNoteUnderflow()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist("artistName")
      .keyType(KeyType.FOUR)
      .difficulty(MusicDifficulty.EZ)
      .category("1ST")
      .level(3)
      .rank(0)
      .bestScore(1_100_000)
      .totalNote(0)
      .addTime(LocalDateTime.now())
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("bestScore overflow 케이스")
  @Test
  void isValidWithBestScoreOverflow()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist("artistName")
      .keyType(KeyType.FOUR)
      .difficulty(MusicDifficulty.EZ)
      .category("1ST")
      .level(3)
      .rank(0)
      .bestScore(1100313)
      .totalNote(312)
      .addTime(LocalDateTime.now())
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("bestScore underflow 케이스")
  @Test
  void isValidWithBestScoreUnderflow()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist("artistName")
      .keyType(KeyType.FOUR)
      .difficulty(MusicDifficulty.EZ)
      .category("1ST")
      .level(3)
      .rank(0)
      .bestScore(1100311)
      .totalNote(312)
      .addTime(LocalDateTime.now())
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("rank underflow 케이스")
  @Test
  void isValidWithRankUnderflow()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist("artistName")
      .keyType(KeyType.FOUR)
      .difficulty(MusicDifficulty.EZ)
      .category("1ST")
      .level(3)
      .rank(-1)
      .bestScore(1100312)
      .totalNote(312)
      .addTime(LocalDateTime.now())
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("level overflow 케이스")
  @Test
  void isValidWithLevelOverflow()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist("artistName")
      .keyType(KeyType.FOUR)
      .difficulty(MusicDifficulty.EZ)
      .category("1ST")
      .level(21)
      .rank(0)
      .bestScore(1100312)
      .totalNote(312)
      .addTime(LocalDateTime.now())
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("level underflow 케이스")
  @Test
  void isValidWithLevelUnderflow()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist("artistName")
      .keyType(KeyType.FOUR)
      .difficulty(MusicDifficulty.EZ)
      .category("1ST")
      .level(-1)
      .rank(0)
      .bestScore(1100312)
      .totalNote(312)
      .addTime(LocalDateTime.now())
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("category null 케이스")
  @Test
  void isValidWithCategoryNull()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist("artistName")
      .keyType(KeyType.FOUR)
      .difficulty(MusicDifficulty.EZ)
      .category(null)
      .level(3)
      .rank(0)
      .bestScore(1100312)
      .totalNote(312)
      .addTime(LocalDateTime.now())
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("musicDifficulty null 케이스")
  @Test
  void isValidWithMusicDifficultyNull()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist("artistName")
      .keyType(KeyType.FOUR)
      .difficulty(null)
      .category("1ST")
      .level(3)
      .rank(0)
      .bestScore(1100312)
      .totalNote(312)
      .addTime(LocalDateTime.now())
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("keyType null 케이스")
  @Test
  void isValidWithKeyTypeNull()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist("artistName")
      .keyType(null)
      .difficulty(MusicDifficulty.EZ)
      .category("1ST")
      .level(3)
      .rank(0)
      .bestScore(1100312)
      .totalNote(312)
      .addTime(LocalDateTime.now())
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("artist null 케이스")
  @Test
  void isValidWithArtistNull()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name("musicName")
      .artist(null)
      .keyType(KeyType.FOUR)
      .difficulty(MusicDifficulty.EZ)
      .category("1ST")
      .level(3)
      .rank(0)
      .bestScore(1100312)
      .totalNote(312)
      .addTime(LocalDateTime.now())
      .build();

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("id null 케이스")
  @Test
  void isValidWithNameNull()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
      .name(null)
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

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertFalse(validWithTrim);
  }

  @DisplayName("정상 케이스")
  @Test
  void isValid()
  {
    // Given
    final MusicInfo musicInfo = MusicInfo.builder()
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

    // When
    final boolean validWithTrim =  musicInfoValidator.isValidWithTrim(musicInfo);

    // Then
    Assertions.assertTrue(validWithTrim);
  }
}