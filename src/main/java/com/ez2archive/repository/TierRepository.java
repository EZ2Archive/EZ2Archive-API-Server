package com.ez2archive.repository;

import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.Tier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TierRepository extends JpaRepository<Tier, Long>
{
  Optional<Tier> findTierByMemberAndKeyType(Member member, KeyType keyType);
}
