package com.ez2achieve.repository;

import com.ez2achieve.entity.KeyType;
import com.ez2achieve.entity.MusicInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MusicInfoRepository extends JpaRepository<MusicInfo, Long>
{
  Long countByKeyTypeAndLevel(KeyType keyType, int level);

  @EntityGraph(attributePaths = {"imageFile"})
  List<MusicInfo> findMusicInfosByKeyTypeOrderByRankDescNameAsc(KeyType keyType);

  @EntityGraph(attributePaths = {"imageFile"})
  List<MusicInfo> findMusicInfosByKeyTypeAndLevelOrderByRankDescNameAsc(KeyType keyType, int level);

  @EntityGraph(attributePaths = {"imageFile"})
  Page<MusicInfo> findMusicInfosByKeyTypeAndNameContaining(KeyType keyType, String name, Pageable pageable);

}
