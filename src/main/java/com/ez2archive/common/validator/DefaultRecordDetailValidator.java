package com.ez2archive.common.validator;

import com.ez2archive.entity.Grade;
import com.ez2archive.entity.RecordDetail;

public class DefaultRecordDetailValidator implements Validator<RecordDetail>
{
  @Override
  public boolean isValid(RecordDetail recordDetail)
  {
    return isValidScore(recordDetail.getScore(), recordDetail.getRecord().getMusic().getBestScore())
      && isBadgeValid(recordDetail.isAllCool(), recordDetail.isNoMiss())
      && isGradeValid(recordDetail.getGrade());
  }

  private boolean isValidScore(int score, int bestScore)
  {
    return (score >= 0 && score <= bestScore)
      && (score >= Grade.F.score());
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
