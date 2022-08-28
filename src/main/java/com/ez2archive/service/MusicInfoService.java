package com.ez2archive.service;

import com.ez2archive.common.exception.business.IllegalValueException;
import com.ez2archive.common.exception.business.ResourceNotFoundException;
import com.ez2archive.common.validator.DefaultMusicInfoValidator;
import com.ez2archive.common.validator.Validator;
import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.repository.MusicInfoRepository;
import com.ez2archive.repository.RecordRepository;
import com.ez2archive.repository.TierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MusicInfoService
{
  private final MusicInfoRepository musicInfoRepository;
  private final TierRepository tierRepository;
  private final RecordRepository recordRepository;

  private final Validator<MusicInfo> musicInfoValidator;

  public MusicInfo findById(Long id)
  {
    return musicInfoRepository.findById(id)
      .orElseThrow( () -> new ResourceNotFoundException("음원 정보가 존재하지 않습니다.") );
  }

  public Page<MusicInfo> findByKeyTypeAndNameContaining(KeyType keyType, String keyword, Pageable pageable)
  {
    Page<MusicInfo> musicInfoList = musicInfoRepository.findMusicInfosByKeyTypeAndNameContaining(keyType, keyword, pageable);

    return musicInfoList;
  }

  @Transactional
  public Long save(MusicInfo musicInfo)
  {
    // calculate best score
    musicInfo.setBestScore( DefaultMusicInfoValidator.CONSTANT_BEST_SCORE + musicInfo.getTotalNote() );
    musicInfo.setAddTime(LocalDateTime.now());

    if(!musicInfoValidator.isValidWithTrim(musicInfo)) throw new IllegalValueException("잘못된 음원정보 양식의 요청입니다.");

    musicInfoRepository.save(musicInfo);

    return musicInfo.getId();
  }

  @Transactional
  public Long update(MusicInfo musicInfo)
  {
    MusicInfo findMusicInfo = this.findById(musicInfo.getId());

    if(!musicInfoValidator.isValidWithTrim(musicInfo)) throw new IllegalValueException("잘못된 음원정보 양식의 요청입니다.");

    if( findMusicInfo.getLevel() != musicInfo.getLevel() )
    {
      // TODO --> 레벨이 변했을 경우, 해당 음원의 티어점수 업데이트 (DB 프로시저 개발 필요)
      throw new UnsupportedOperationException();
    }

    if( findMusicInfo.getTotalNote() != musicInfo.getTotalNote() )
    {
      // 토탈 노트 수가 변했을 경우, 해당 음원의 이전 플레이 기록과 티어점수를 모두 삭제
      tierRepository.deleteTiersByMusic(findMusicInfo);
      recordRepository.deleteRecordsByMusic(findMusicInfo);
    }

    musicInfo.setAddTime(findMusicInfo.getAddTime());
    musicInfoRepository.save(musicInfo);

    return musicInfo.getId();
  }

  @Transactional
  public void delete(Long musicInfoId)
  {
    MusicInfo findMusicInfo = this.findById(musicInfoId);

    musicInfoRepository.delete(findMusicInfo);
  }

  public List<MusicInfo> getRankTableListByKeyType(KeyType keyType)
  {
    return musicInfoRepository.findMusicInfosByKeyTypeOrderByRankDescNameAsc(keyType);
  }

  public List<MusicInfo> getRankTableListByKeyTypeAndLevel(KeyType keyType, int level)
  {
    return musicInfoRepository.findMusicInfosByKeyTypeAndLevelOrderByRankDescNameAsc(keyType, level);
  }

  private boolean isTierAffected(MusicInfo actualMusicInfo, MusicInfo updateMusicInfo)
  {
    return actualMusicInfo.getLevel()   != updateMusicInfo.getLevel()
      && actualMusicInfo.getTotalNote() != updateMusicInfo.getTotalNote();
  }
}
