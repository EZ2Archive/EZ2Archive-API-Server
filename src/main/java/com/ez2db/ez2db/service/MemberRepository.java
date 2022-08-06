package com.ez2db.ez2db.service;

import com.ez2db.ez2db.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRepository
{
  private final EntityManager em;

  @Transactional
  public Long save(Member member)
  {
    em.persist(member);

    return member.getId();
  }
}
