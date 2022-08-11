package com.ez2db.service;

import com.ez2db.entity.AchieveVO;
import com.ez2db.entity.KeyType;
import com.ez2db.entity.OverallVO;
import com.ez2db.repository.AchievementRepository;
import com.ez2db.repository.MusicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementService
{
  private final AchievementRepository achieveRepository;
  private final MusicInfoRepository musicInfoRepository;

  public List<AchieveVO> findAchievementList(String userId, KeyType keyType, int level)
  {
    return achieveRepository.findAchieveListByUserIdWithKeyTypeWithLevel(userId, keyType, level);
  }

  public OverallVO findAchievementOverall(String userId, KeyType keyType, int level)
  {
    Long totalCnt = musicInfoRepository.findMusicInfoCountByKeyTypeWithLevel(keyType, level);

    OverallVO overallVO = achieveRepository.findOverallByUserIdWithKeyTypeWithLevel(userId, keyType, level);

    overallVO.setTotalCnt(totalCnt);

    return overallVO;
  }

}
