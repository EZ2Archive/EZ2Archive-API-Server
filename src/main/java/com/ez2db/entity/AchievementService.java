package com.ez2db.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementService
{
  private final AchieveRepository aachieveRepository;

  public List<AchieveVO> findAchievementList(String userId, KeyType keyType, int level)
  {
    return aachieveRepository.findByUserIdWithKeyTypeWithLevel(userId, keyType, level);
  }

}
