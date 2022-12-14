package com.ez2archive.service;

import com.ez2archive.common.exception.business.IllegalValueException;
import com.ez2archive.common.exception.business.ResourceNotFoundException;
import com.ez2archive.common.validator.Validator;
import com.ez2archive.dto.achieve.AchieveDTO;
import com.ez2archive.dto.achieve.AchieveDetailDTO;
import com.ez2archive.dto.achieve.OverallDTO;
import com.ez2archive.dto.tier.RecordDetailDTO;
import com.ez2archive.dto.tier.TierAverageDTO;
import com.ez2archive.entity.*;
import com.ez2archive.handler.TierHandler;
import com.ez2archive.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
  private final DtoRepository dtoRepository;

  private final Validator<RecordDetail> recordDetailValidator;

  private final TierHandler<TierGrade> tierHandler;

  public List<AchieveDTO> findAchievementList(String userId, KeyType keyType, int level)
  {
    Member member = memberRepository.findByUserId(userId)
      .orElseThrow( () -> new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.") );

    return achieveRepository.findAchieveListByUserIdWithKeyTypeWithLevel(userId, keyType, level);
  }

  public OverallDTO findAchievementOverall(String userId, KeyType keyType, int level)
  {
    Long totalCnt = musicInfoRepository.countByKeyTypeAndLevel(keyType, level);

    OverallDTO overallDTO = achieveRepository.findOverallByUserIdWithKeyTypeWithLevel(userId, keyType, level);

    overallDTO.setTotalCnt(totalCnt);

    return overallDTO;
  }

  public List<RecordDetail> findAchievementHistory(String userId, Long musicInfoId)
  {
    Member findMember = memberRepository.findByUserId(userId)
      .orElseThrow( () -> new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.") );

    MusicInfo findMusicInfo = musicInfoRepository.findById(musicInfoId)
      .orElseThrow( () -> new ResourceNotFoundException("음원 정보가 존재하지 않습니다.") );

    return recordRepository.findRecordsByMemberAndMusic(findMember, findMusicInfo)
      .stream()
      .map(Record::getRecordDetail)
      .collect(Collectors.toList());
  }

  @Transactional
  public void saveAchievementRecord(String userId, RecordDetail recordDetail)
  {
    Member findMember = memberRepository.findByUserId(userId)
      .orElseThrow( () -> new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.") );

    MusicInfo findMusicInfo = musicInfoRepository.findById(recordDetail.getMusicInfoId())
      .orElseThrow( () -> new ResourceNotFoundException("음원 정보가 존재하지 않습니다.") );

    final KeyType keyType = findMusicInfo.getKeyType();

    // setting RecordDetail
    recordDetail.setPercentage( Math.round(((float)recordDetail.getScore() / findMusicInfo.getBestScore() * 100f) * 1000f) / 1000f );
    recordDetail.setAddTime(LocalDateTime.now());
    recordDetail.setGrade( Grade.of(recordDetail.getScore()) );
    recordDetail.setPoint( tierHandler.getTierPointAsScore(keyType, findMusicInfo.getBestScore(), findMusicInfo.getLevel(), recordDetail.getScore()) );

    Record findBeforeRecord = recordRepository.findTopByMemberAndMusicOrderByAddTimeDesc(findMember, findMusicInfo)
      .orElseGet( Record::new );

    Record record = Record.builder()
      .member(findMember)
      .music(findMusicInfo)
      .recordDetail(recordDetail)
      .addTime(LocalDateTime.now())
      .build();
    recordDetail.setRecord(record);

    // 유효하지 않거나 기존에 기록된 점수보다 낮을 경우 기록하지 않는다.
    if( !recordDetailValidator.isValid(recordDetail) ||
        ( findBeforeRecord.getId() != null &&
          findBeforeRecord.getRecordDetail().getScore() >= recordDetail.getScore() ) )
      throw new IllegalValueException("잘못된 요청의 양식입니다.");


    recordRepository.save(record);

    // totalPoint = 사용자, 키타입으로 그룹핑한 티어포인트들 중 최고점수 50개의 합
    this.updateTier(findMember, keyType);
  }

  @Transactional
  public void deleteRecord(String userId, Long recordDetailId)
  {
    Member findMember = memberRepository.findByUserId(userId)
      .orElseThrow( () -> new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.") );

    RecordDetail findRecordDetail = recordDetailRepository.findById(recordDetailId)
      .orElseThrow( () -> new ResourceNotFoundException("기록이 존재하지 않습니다.") );

    Record findRecord = findRecordDetail.getRecord();

    KeyType keyType = findRecord.getMusic().getKeyType();

    recordRepository.delete(findRecord);
    this.updateTier(findMember, keyType);
  }

  // =========================================================================================================================
  // ======================================================== PRIVATE ========================================================
  // =========================================================================================================================

  public AchieveDetailDTO findAchievementDetail(String userId, Long musicInfoId)
  {
    Member findMember = memberRepository.findByUserId(userId)
      .orElseThrow( () -> new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.") );

    MusicInfo findMusicInfo = musicInfoRepository.findById(musicInfoId)
      .orElseThrow( () -> new ResourceNotFoundException("음원 정보가 존재하지 않습니다.") );

    // 사용자의 현재 티어구간 조회
    Tier tier = tierRepository.findTierByMemberAndKeyType(findMember, findMusicInfo.getKeyType())
      .orElseGet( () ->
        Tier.builder()
          .tierGrade(TierGrade.BEGINNER2)
          .build()
      );
    List<TierGrade> tierGradeList = TierGrade.ofGroup(tier.getTierGrade());

    // 사용자의 현재 티어구간 평균스코어, 평균티어점수 조회
    TierAverageDTO tierAverage = dtoRepository.findAvgRecordByMusicInfoIdInTierGrade(tierGradeList, findMusicInfo.getKeyType(), musicInfoId)
      .orElseGet(TierAverageDTO::new);

    return AchieveDetailDTO.builder()
      .musicInfoId(musicInfoId)
      .name(findMusicInfo.getName())
      .avgScore(tierAverage.getAverageScore())
      .avgTierPoint(tierAverage.getAveragePoint())
      .build();
  }

  /**
   * 현재 티어를 갱신
   */
  private void updateTier(Member member, KeyType keyType) throws IllegalStateException
  {
    final double totalPoint = dtoRepository.findTop50RecordDetailDTOsByMemberAndKeyType(member, keyType)
      .stream()
      .map(RecordDetailDTO::getPoint)
      .reduce(Double::sum)
      .orElseThrow(IllegalStateException::new);
    final double changePoint = tierHandler.getChangePoint(keyType, totalPoint);

    Tier findTier = tierRepository.findTierByMemberAndKeyType(member, keyType)
      .orElseGet( () ->
        Tier.builder()
          .member(member)
          .keyType(keyType)
          .addTime(LocalDateTime.now())
          .build()
      );
    findTier.setTotalPoint(totalPoint);
    findTier.setChangePoint(changePoint);
    findTier.setTierGrade( tierHandler.getCurrentGrade(changePoint) );
    findTier.setUntilNextTier( tierHandler.getPointUntilNextTier(changePoint) );
    findTier.setLastModifyTime(LocalDateTime.now());

    tierRepository.save(findTier);
  }
}
