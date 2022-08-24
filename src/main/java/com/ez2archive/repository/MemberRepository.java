package com.ez2archive.repository;

import com.ez2archive.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
  Optional<Member> findByUserId(String userId);
}
