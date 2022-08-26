package com.ez2archive.repository;

import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.MusicInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MusicInfoRepository extends JpaRepository<MusicInfo, Long>
{
  Long countByKeyTypeAndLevel(KeyType keyType, int level);

  List<MusicInfo> findMusicInfosByKeyTypeOrderByRankDescNameAsc(KeyType keyType);

  List<MusicInfo> findMusicInfosByKeyTypeAndLevelOrderByRankDescNameAsc(KeyType keyType, int level);

  Page<MusicInfo> findMusicInfosByKeyTypeAndNameContaining(KeyType keyType, String name, Pageable pageable);

}
