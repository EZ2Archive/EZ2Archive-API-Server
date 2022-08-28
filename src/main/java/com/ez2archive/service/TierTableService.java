package com.ez2archive.service;

import com.ez2archive.common.exception.business.ResourceNotFoundException;
import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.Tier;
import com.ez2archive.entity.TierGrade;
import com.ez2archive.handler.TierHandler;
import com.ez2archive.repository.MemberRepository;
import com.ez2archive.repository.TierRepository;
import com.ez2archive.vo.TierInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TierTableService
{
  private final MemberRepository memberRepository;
  private final TierRepository tierRepository;

  private final TierHandler<TierGrade> tierHandler;

  public TierInfoVO getTierByUserIdAndKeyType(String userId, KeyType keyType)
  {
    Optional<Member> findMember = memberRepository.findByUserId(userId);

    if( findMember.isEmpty() ) throw new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.");

    final Optional<Double> findTotalPoint = tierRepository.findSumPointByMemberAndKeyType(findMember.get(), keyType);

    double totalPoint = findTotalPoint.orElse(0d);

    final double changePoint = tierHandler.getChangePoint(keyType, totalPoint);

    return TierInfoVO.builder()
      .name(findMember.get().getName())
      .tierGrade(tierHandler.getCurrentGrade(changePoint))
      .totalPoint(totalPoint)
      .changePoint(changePoint)
      .untilNextTier(tierHandler.getPointUntilNextTier(changePoint))
      .build();
  }

  public List<Tier> getTierListByUserIdAndKeyType(String userId, KeyType keyType)
  {
    Optional<Member> findMember = memberRepository.findByUserId(userId);

    if( findMember.isEmpty() ) throw new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.");

    return tierRepository.findTiersByMemberAndMusicKeyType(findMember.get(), keyType);
  }
}