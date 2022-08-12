package com.ez2db.service;

import com.ez2db.common.exception.business.IllegalValueException;
import com.ez2db.common.exception.business.ResourceNotFoundException;
import com.ez2db.common.validator.Validator;
import com.ez2db.entity.Member;
import com.ez2db.entity.MusicInfo;
import com.ez2db.entity.Record;
import com.ez2db.entity.RecordDetail;
import com.ez2db.repository.MemberRepository;
import com.ez2db.repository.MusicInfoRepository;
import com.ez2db.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService
{
  private final RecordRepository recordRepository;
  private final MusicInfoRepository musicInfoRepository;
  private final MemberRepository memberRepository;
  private final Validator<Record> validator;

  public Long save(String userId, Long musicInfoId, RecordDetail recordDetail)
  {
    Record record = new Record();
    Optional<Member> findMember = memberRepository.findByUserId(userId);

    Optional<MusicInfo> findMusicInfo = musicInfoRepository.findById(musicInfoId);

    if(findMember.isEmpty()) throw new ResourceNotFoundException("사용자 정보를 찾지 못하였습니다.");
    if(findMusicInfo.isEmpty()) throw new ResourceNotFoundException("음원 정보를 찾지 못하였습니다.");

    record.setMember(findMember.get());
    record.setMusic(findMusicInfo.get());
    record.setRecordDetail(recordDetail);

    if( !validator.isValid(record) ) throw new IllegalValueException("잘못된 점수 양식의 요청입니다.");

    return recordRepository.save(record);
  }
}
