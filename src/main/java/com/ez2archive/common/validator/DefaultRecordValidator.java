package com.ez2archive.common.validator;

import com.ez2archive.entity.Grade;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.entity.Record;

public class DefaultRecordValidator implements Validator<Record>
{
  @Override
  public boolean isValid(Record record)
  {
    return isMusicValid(record.getMusic())
      && isMemberValid(record.getMember())
      && isScoreValid(record.getRecordDetail().getScore(), record.getMusic().getBestScore())
      && isPercentageValid(record.getRecordDetail().getPercentage())
      && isBadgeValid(record.getRecordDetail().isAllCool(), record.getRecordDetail().isNoMiss())
      && isGradeValid(record.getRecordDetail().getGrade());
  }

  private boolean isMusicValid(MusicInfo music)
  {
    return music != null
      && music.getId() != 0;
  }

  private boolean isMemberValid(Member member)
  {
    return member != null
      && member.getUserId() != null
      && !member.getUserId().isEmpty();
  }

  private boolean isScoreValid(int score, int bestScore)
  {
    return score >= 0 && score <= bestScore;
  }

  private boolean isPercentageValid(double percentage)
  {
    return percentage > 0.00d && percentage <= 100.00d;
  }

  private boolean isBadgeValid(boolean allCool, boolean noMiss)
  {
    if(allCool)
    {
      return noMiss;
    }
    return true;
  }

  private boolean isGradeValid(Grade grade)
  {
    return grade != null;
  }
}
