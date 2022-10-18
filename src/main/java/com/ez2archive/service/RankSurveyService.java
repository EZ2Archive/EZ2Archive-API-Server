package com.ez2archive.service;

import com.ez2archive.common.exception.business.IllegalValueException;
import com.ez2archive.common.exception.business.ResourceNotFoundException;
import com.ez2archive.common.validator.Validator;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.MusicInfo;
import com.ez2archive.entity.RankSurvey;
import com.ez2archive.repository.MemberRepository;
import com.ez2archive.repository.MusicInfoRepository;
import com.ez2archive.repository.RankSurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankSurveyService
{
  private final MemberRepository memberRepository;
  private final MusicInfoRepository musicInfoRepository;
  private final RankSurveyRepository rankSurveyRepository;

  private final Validator<RankSurvey> rankValidator;

  @Transactional
  public void save(String userId, Long musicInfoId, int point)
  {
    Optional<Member> findMember = memberRepository.findByUserId(userId);
    Optional<MusicInfo> findMusicInfo = musicInfoRepository.findById(musicInfoId);

    if( findMember.isEmpty() || findMusicInfo.isEmpty() ) throw new ResourceNotFoundException("사용자 혹은 음원정보를 찾을 수 없습니다.");

    RankSurvey rankSurvey = rankSurveyRepository.findRankSurveyByMusicAndMember(findMusicInfo.get(), findMember.get())
      .orElseGet(() ->
        RankSurvey.builder()
          .music(findMusicInfo.get())
          .member(findMember.get())
          .addTime(LocalDateTime.now())
          .build()
      );
    rankSurvey.setPoint(point);
    rankSurvey.setLastModifyTime(LocalDateTime.now());

    if( !rankValidator.isValid(rankSurvey) ) throw new IllegalValueException("유효하지 않은 랭크 양식입니다.");

    rankSurveyRepository.save(rankSurvey);
  }
}
