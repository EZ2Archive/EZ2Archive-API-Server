package com.ez2archive.common.validator;

import com.ez2archive.entity.Grade;
import com.ez2archive.entity.Record;
import com.ez2archive.entity.RecordDetail;

public class DefaultRecordDetailValidator implements Validator<RecordDetail>
{
  @Override
  public boolean isValid(RecordDetail recordDetail)
  {
    return isValidRecord(recordDetail.getRecord())
      && isValidMusicInfoId(recordDetail.getMusicInfoId(), recordDetail.getRecord().getMusic().getId())
      && isValidScore(recordDetail.getScore(), recordDetail.getRecord().getMusic().getBestScore())
      && isValidBadge(recordDetail.isAllCool(), recordDetail.isNoMiss())
      && isValidPercentage(recordDetail.getPercentage())
      && isValidGrade(recordDetail.getGrade());
  }

  private boolean isValidRecord(Record record)
  {
    return record != null
      && record.getMusic() != null;
  }

  private boolean isValidMusicInfoId(Long musicInfoId, Long id)
  {
    return musicInfoId.equals(id);
  }

  private boolean isValidScore(int score, int bestScore)
  {
    return (score >= 0 && score <= bestScore)
      && (score >= Grade.F.score());
  }

  private boolean isValidPercentage(float percentage)
  {
    return percentage <= 100f
      && percentage >= 50f;
  }

  private boolean isValidBadge(boolean allCool, boolean noMiss)
  {
    if(allCool)
    {
      return noMiss;
    }
    return true;
  }

  private boolean isValidGrade(Grade grade)
  {
    return grade != null;
  }
}
