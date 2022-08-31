package com.ez2archive.repository;

import com.ez2archive.entity.Member;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.entity.Record;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>
{
  Optional<Record> findTopByMemberAndMusicOrderByAddTimeDesc(Member member, MusicInfo music);

  void deleteRecordsByMusic(MusicInfo musicInfo);

  @EntityGraph(attributePaths = {"recordDetail"})
  List<Record> findRecordsByMemberAndMusic(Member member, MusicInfo music);

}
