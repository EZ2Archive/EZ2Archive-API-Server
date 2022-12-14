package com.ez2archive.service;

import com.ez2archive.common.exception.business.ResourceNotFoundException;
import com.ez2archive.dto.tier.RecordDetailDTO;
import com.ez2archive.dto.tier.TierInfoDTO;
import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.Tier;
import com.ez2archive.entity.TierGrade;
import com.ez2archive.repository.DtoRepository;
import com.ez2archive.repository.MemberRepository;
import com.ez2archive.repository.TierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TierTableService
{
  private final MemberRepository memberRepository;
  private final TierRepository tierRepository;
  private final DtoRepository dtoRepository;

  public TierInfoDTO findTierInfo(String userId, KeyType keyType)
  {
    Member findMember = memberRepository.findByUserId(userId)
      .orElseThrow( () -> new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.") );

    Tier tier = tierRepository.findTierByMemberAndKeyType(findMember, keyType)
      .orElseGet( () ->
        Tier.builder()
          .member(findMember)
          .keyType(keyType)
          .tierGrade(TierGrade.BEGINNER2)
          .totalPoint(0d)
          .changePoint(0d)
          .untilNextTier(TierGrade.BEGINNER1.score())
          .build()
      );

    return TierInfoDTO.of(tier, findMember);
  }

  public List<RecordDetailDTO> getTierListByUserIdAndKeyType(String userId, KeyType keyType)
  {
    Member findMember = memberRepository.findByUserId(userId)
      .orElseThrow( () -> new ResourceNotFoundException("사용자 정보가 존재하지 않습니다.") );

    return dtoRepository.findTop50RecordDetailDTOsByMemberAndKeyType(findMember, keyType);
  }
}
