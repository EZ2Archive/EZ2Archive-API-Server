package com.ez2archive.common.validator;

import com.ez2archive.entity.MusicInfo;

import java.time.LocalDateTime;

public class DefaultMusicInfoValidator implements Validator<MusicInfo>
{
  public static final int CONSTANT_BEST_SCORE = 1_100_000;
  @Override
  public boolean isValid(MusicInfo musicInfo)
  {
    // [WARN] 임의적인 로직 순서 변경 금지
    return isNameValid(musicInfo.getName())
      && isArtistValid(musicInfo.getArtist())
      && isLevelValid(musicInfo.getLevel())
      && isRankValid(musicInfo.getRank())
      && isTotalNoteValid(musicInfo.getTotalNote())
      && isBestScoreValid(musicInfo.getBestScore(), musicInfo.getTotalNote())
      && isAddTimeValid(musicInfo.getAddTime());
  }

  private boolean isNameValid(String name)
  {
    return name != null && !name.isEmpty();
  }

  private boolean isArtistValid(String artist)
  {
    return artist != null && !artist.isEmpty();
  }

  private boolean isLevelValid(int level)
  {
    return level > 0 && level <= 20;
  }

  private boolean isRankValid(int rank)
  {
    return rank >= 0;
  }

  private boolean isTotalNoteValid(int totalNote)
  {
    return totalNote >= 0;
  }

  private boolean isBestScoreValid(int bestScore, int totalNote)
  {
    return bestScore == (CONSTANT_BEST_SCORE + totalNote);
  }

  private boolean isAddTimeValid(LocalDateTime addTime)
  {
    return addTime != null;
  }
}
