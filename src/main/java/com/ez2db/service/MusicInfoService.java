package com.ez2db.service;

import com.ez2db.entity.KeyType;
import com.ez2db.entity.MusicInfo;
import com.ez2db.repository.MusicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicInfoService
{
  private final MusicInfoRepository repository;

  private static final Sort SORT_RANK_DESC = Sort.by(Sort.Direction.DESC, "rank");

  public List<MusicInfo> getRankTableListByKeyTypeAndLevel(KeyType keyType, int level)
  {
    return repository.findMusicInfosByKeyTypeAndLevel(keyType, level, SORT_RANK_DESC);
  }

  public List<MusicInfo> getRankTableListByKeyType(KeyType keyType)
  {
    return repository.findMusicInfosByKeyType(keyType, SORT_RANK_DESC);
  }

}
