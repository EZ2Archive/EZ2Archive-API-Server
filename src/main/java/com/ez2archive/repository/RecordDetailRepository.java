package com.ez2archive.repository;

import com.ez2archive.entity.RecordDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordDetailRepository extends JpaRepository<RecordDetail, Long>
{
  @Query("SELECT rd FROM RecordDetail rd JOIN rd.recordList r JOIN r.member m WHERE m.userId = :userId AND rd.musicInfoId = :musicInfoId ORDER BY rd.addTime DESC")
  List<RecordDetail> findRecordDetailsByUserIdAndMusicInfoId(@Param("userId") String userId, @Param("musicInfoId") Long musicInfoId);
}
