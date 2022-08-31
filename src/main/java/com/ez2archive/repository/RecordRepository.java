package com.ez2archive.repository;

import com.ez2archive.entity.*;
import com.ez2archive.dto.tier.TierAverageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>
{
  Optional<Record> findTopByMemberAndMusicOrderByAddTimeDesc(Member member, MusicInfo music);

  void deleteRecordsByMusic(MusicInfo musicInfo);

  @Query("SELECT new com.ez2archive.dto.tier.TierAverageDTO(mi.id, mi.name, avg(rd.score), avg(tp.point)) FROM Record r JOIN r.tierPoint tp JOIN tp.music mi JOIN tp.member m JOIN r.recordDetail rd WHERE m IN (SELECT m FROM Tier t JOIN t.member m WHERE t.tierGrade IN :tierGradeList AND t.keyType = :keyType) GROUP BY mi.id, mi.name, mi.level HAVING mi.level = :level")
  List<TierAverageDTO> findAvgRecordByMusicInTierGradeAndKeyType(@Param("tierGradeList") List<TierGrade> tierGrade, @Param("keyType") KeyType keyType, @Param("level") int level);
}
