package com.ez2db.repository;

import com.ez2db.entity.RecordDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordDetailRepository extends JpaRepository<RecordDetail, Long>
{
  @Query("SELECT rd FROM RecordDetail rd JOIN rd.recordList r JOIN r.member m WHERE m.userId = :userId AND rd.musicInfoId = :musicInfoId")
  List<RecordDetail> findRecordDetailsByUserIdAndMusicInfoId( String userId, Long musicInfoId);
}
