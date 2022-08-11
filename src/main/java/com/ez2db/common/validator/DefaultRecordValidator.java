package com.ez2db.common.validator;

import com.ez2db.entity.Grade;
import com.ez2db.entity.Member;
import com.ez2db.entity.MusicInfo;
import com.ez2db.entity.Record;

public class DefaultRecordValidator implements Validator<Record>
{
  @Override
  public boolean isValid(Record record)
  {
    return isMusicValid(record.getMusic())
      && isMemberValid(record.getMember())
      && isScoreValid(record.getRecordDetail().getScore(), record.getMusic().getBestScore())
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
      && member.getUserId().isEmpty();
  }

  private boolean isScoreValid(int score, int bestScore)
  {
    return score >= 0 && score <= bestScore;
  }

  private boolean isGradeValid(Grade grade)
  {
    return grade != null;
  }
}
