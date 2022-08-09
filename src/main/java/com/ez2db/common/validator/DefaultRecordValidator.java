package com.ez2db.common.validator;

import com.ez2db.entity.Grade;
import com.ez2db.entity.Member;
import com.ez2db.entity.MusicInfo;
import com.ez2db.entity.Record;

public class DefaultRecordValidator implements Validator<Record>
{
  @Override
  public boolean isValidate(Record record)
  {
    return isMusicAvailable(record.getMusic())
      && isMemberAvailable(record.getMember())
      && isScoreAvailable(record.getScore(), record.getMusic().getBestScore())
      && isGradeAvailable(record.getGrade());
  }

  private boolean isMusicAvailable(MusicInfo music)
  {
    return music != null
      && music.getId() != 0;
  }

  private boolean isMemberAvailable(Member member)
  {
    return member != null
      && member.getUserId() != null
      && member.getUserId().isEmpty();
  }

  private boolean isScoreAvailable(int score, int bestScore)
  {
    return score >= 0 && score <= bestScore;
  }

  private boolean isGradeAvailable(Grade grade)
  {
    return grade != null;
  }
}
