package com.ez2db.service;

import com.ez2db.common.exception.business.ResourceNotFoundException;
import com.ez2db.entity.KeyType;
import com.ez2db.entity.Member;
import com.ez2db.entity.Tier;
import com.ez2db.repository.MemberRepository;
import com.ez2db.repository.TierRepository;
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

  public List<Tier> getTierListByUserIdAndKeyType(String userId, KeyType keyType)
  {
    Optional<Member> findMember = memberRepository.findByUserId(userId);

    if( findMember.isEmpty() ) throw new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.");

    return tierRepository.findTiersByMemberAndKeyType(findMember.get(), keyType);
  }
}
