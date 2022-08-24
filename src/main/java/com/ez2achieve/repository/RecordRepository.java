package com.ez2achieve.repository;

import com.ez2achieve.entity.MusicInfo;
import com.ez2achieve.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>
{
  void deleteRecordsByMusic(MusicInfo musicInfo);
}
