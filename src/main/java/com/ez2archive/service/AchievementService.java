package com.ez2archive.service;

import com.ez2archive.common.exception.business.IllegalValueException;
import com.ez2archive.common.exception.business.ResourceNotFoundException;
import com.ez2archive.common.validator.Validator;
import com.ez2archive.entity.*;
import com.ez2archive.handler.TierHandler;
import com.ez2archive.repository.*;
import com.ez2archive.vo.AchieveVO;
import com.ez2archive.vo.OverallVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AchievementService
{
  private final AchievementRepository achieveRepository;
  private final MusicInfoRepository musicInfoRepository;
  private final RecordRepository recordRepository;
  private final RecordDetailRepository recordDetailRepository;
  private final TierRepository tierRepository;

  private final MemberRepository memberRepository;

  private final Validator<Record> recordValidator;

  private final TierHandler<TierGrade> tierHandler;

  public List<AchieveVO> findAchievementList(String userId, KeyType keyType, int level)
  {
    return achieveRepository.findAchieveListByUserIdWithKeyTypeWithLevel(userId, keyType, level);
  }

  public OverallVO findAchievementOverall(String userId, KeyType keyType, int level)
  {
    Long totalCnt = musicInfoRepository.countByKeyTypeAndLevel(keyType, level);

    OverallVO overallVO = achieveRepository.findOverallByUserIdWithKeyTypeWithLevel(userId, keyType, level);

    overallVO.setTotalCnt(totalCnt);

    return overallVO;
  }

  public List<RecordDetail> findAchievementHistory(String userId, Long musicInfoId)
  {
    return recordDetailRepository.findRecordDetailsByUserIdAndMusicInfoId(userId, musicInfoId);
  }

  @Transactional
  public void saveAchievementRecord(String userId, RecordDetail recordDetail)
  {
    Optional<Member> findMember = memberRepository.findByUserId(userId);

    if( findMember.isEmpty() ) throw new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.");

    Optional<MusicInfo> findMusicInfo = musicInfoRepository.findById(recordDetail.getMusicInfoId());

    if( findMusicInfo.isEmpty() ) throw new ResourceNotFoundException("음원 정보가 존재하지 않습니다.");

    final Member    member    = findMember.get();
    final MusicInfo musicInfo = findMusicInfo.get();

    final Record record = new Record();
    record.setMember(findMember.get());
    record.setMusic(musicInfo);
    recordDetail.setPercentage( Math.round(((float)recordDetail.getScore() / musicInfo.getBestScore() * 100f) * 1000f) / 1000f );
    recordDetail.setAddTime(LocalDateTime.now());
    record.setRecordDetail(recordDetail);
    record.setAddTime(LocalDateTime.now());

    if( !recordValidator.isValidWithTrim(record) ) throw new IllegalValueException("잘못된 요청의 양식입니다.");

    Optional<Tier> findTier = tierRepository.findTierByMemberAndMusic(member, musicInfo);

    final Tier tier;

    if( findTier.isEmpty() )
    {
      tier = new Tier();
      tier.setMusic(musicInfo);
      tier.setAddTime(LocalDateTime.now());
      tier.setLastUpdateTime(LocalDateTime.now());
    }
    else
    {
      tier = findTier.get();
      tier.setLastUpdateTime(LocalDateTime.now());
    }
    tier.setScore(recordDetail.getScore());
    tier.setMember(member);
    tier.setPoint(tierHandler.getPointAsScore( musicInfo.getKeyType(), musicInfo.getBestScore(), musicInfo.getLevel(), recordDetail.getScore() ));

    tierRepository.save(tier);
    recordRepository.save(record);
  }

}