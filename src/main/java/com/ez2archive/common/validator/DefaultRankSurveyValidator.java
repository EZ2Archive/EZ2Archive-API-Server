package com.ez2archive.common.validator;

import com.ez2archive.entity.Member;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.entity.RankSurvey;

import java.time.LocalDateTime;

public class DefaultRankSurveyValidator implements Validator<RankSurvey>
{
  @Override
  public boolean isValid(RankSurvey rankSurvey)
  {
    return isValidMusicInfo(rankSurvey.getMusic())
      && isValidMember(rankSurvey.getMember())
      && isValidAddTime(rankSurvey.getAddTime())
      && isValidLastModifyTime(rankSurvey.getLastModifyTime())
    && isValidPoint(rankSurvey.getPoint());
  }

  private boolean isValidMusicInfo(MusicInfo music)
  {
    return music != null
      && music.getId() != null
      && music.getId() >= 0;
  }

  private boolean isValidMember(Member member)
  {
    return member != null
      && member.getId() != null
      && member.getId() >= 0;
  }

  private boolean isValidPoint(int point)
  {
    return point <= 2 && point >= -2;
  }

  private boolean isValidAddTime(LocalDateTime addTime)
  {
    return addTime != null;
  }

  private boolean isValidLastModifyTime(LocalDateTime lastModifyTime)
  {
    return lastModifyTime != null;
  }
}
