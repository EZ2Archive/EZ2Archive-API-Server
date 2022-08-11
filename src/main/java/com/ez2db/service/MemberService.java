package com.ez2db.service;

import com.ez2db.common.exception.business.ResourceNotFoundException;
import com.ez2db.entity.Member;
import com.ez2db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService
{
  private final MemberRepository memberRepository;

  public Member findByUserId(String userId)
  {
    Optional<Member> findMember = memberRepository.findByUserId(userId);

    if( findMember.isEmpty() ) throw new ResourceNotFoundException(userId + " 사용자를 찾을 수 없습니다.");

    return findMember.get();
  }
}
