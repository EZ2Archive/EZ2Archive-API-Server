package com.ez2db.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementService
{
  private final ArchiveRepository archiveRepository;

  public List<AchieveVO> findAchievementList(String userId, KeyType keyType, int level)
  {
    return archiveRepository.findByUserIdWithKeyTypeWithLevel(userId, keyType, level);
  }

}
