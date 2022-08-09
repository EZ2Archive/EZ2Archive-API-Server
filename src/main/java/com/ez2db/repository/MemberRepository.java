package com.ez2db.repository;

import com.ez2db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
  Optional<Member> findByUserId(String userId);
}