package com.ez2db.service;

import com.ez2db.common.exception.business.IllegalValueException;
import com.ez2db.common.exception.business.ResourceNotFoundException;
import com.ez2db.common.validator.Validator;
import com.ez2db.entity.*;
import com.ez2db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AchievementService
{
  private final AchievementRepository achieveRepository;
  private final MusicInfoRepository musicInfoRepository;
  private final RecordRepository recordRepository;
  private final RecordDetailRepository recordDetailRepository;

  private final MemberRepository memberRepository;

  private final Validator<Record> recordValidator;

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

  public List<RecordDetail> findAchievementHistory(String userId, Long musicInfoId)
  {
    return recordDetailRepository.findRecordDetailsByUserIdAndMusicInfoId(userId, musicInfoId);
  }

  public void saveAchievementRecord(String userId, RecordDetail recordDetail)
  {
    Optional<Member> findMember = memberRepository.findByUserId(userId);

    if( findMember.isEmpty() ) throw new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.");

    Optional<MusicInfo> findMusicInfo = musicInfoRepository.findById(recordDetail.getMusicInfoId());

    if( findMusicInfo.isEmpty() ) throw new ResourceNotFoundException("음원 정보가 존재하지 않습니다.");

    final Record record = new Record();
    record.setMember(findMember.get());
    record.setMusic(findMusicInfo.get());
    recordDetail.setAddTime(LocalDateTime.now());
    record.setRecordDetail(recordDetail);
    record.setAddTime(LocalDateTime.now());

    if( !recordValidator.isValid(record) ) throw new IllegalValueException("잘못된 요청의 양식입니다.");

    recordRepository.save(record);
  }

}
