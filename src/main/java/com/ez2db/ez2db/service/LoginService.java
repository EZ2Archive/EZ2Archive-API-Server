package com.ez2db.ez2db.service;

import com.ez2db.ez2db.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService
{
  private final MemberRepository repository;

  public Long sinUp(Member member)
  {
    return repository.save(member);
  }
}
