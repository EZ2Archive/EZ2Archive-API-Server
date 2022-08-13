package com.ez2db.repository;

import com.ez2db.entity.KeyType;
import com.ez2db.entity.MusicInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MusicInfoRepository extends JpaRepository<MusicInfo, Long>
{
  Long countByKeyTypeAndLevel(KeyType keyType, int level);

  @Query("SELECT mi FROM MusicInfo mi JOIN FETCH mi.imageFile i WHERE mi.keyType = :keyType")
  List<MusicInfo> findMusicInfosByKeyType(KeyType keyType, Sort sort);

  @Query("SELECT mi FROM MusicInfo mi JOIN FETCH mi.imageFile f WHERE mi.keyType = :keyType AND mi.level = :level")
  List<MusicInfo> findMusicInfosByKeyTypeAndLevel(KeyType keyType, int level, Sort sort);

}
