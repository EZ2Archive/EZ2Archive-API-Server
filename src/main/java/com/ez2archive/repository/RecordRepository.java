package com.ez2archive.repository;

import com.ez2archive.entity.MusicInfo;
import com.ez2archive.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>
{
  void deleteRecordsByMusic(MusicInfo musicInfo);
}
