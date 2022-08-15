package com.ez2db.repository;

import com.ez2db.entity.KeyType;
import com.ez2db.entity.Member;
import com.ez2db.entity.MusicInfo;
import com.ez2db.entity.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TierRepository extends JpaRepository<Tier, Long>
{
  Optional<Tier> findTierByMemberAndMusic(Member member, MusicInfo music);

  @Query("SELECT t FROM Tier t JOIN FETCH t.member m JOIN FETCH t.music mi JOIN FETCH mi.imageFile i WHERE t.member = :member AND mi.keyType = :keyType")
  List<Tier> findTiersByMemberAndKeyType(Member member, KeyType keyType);

  @Query("SELECT sum(t.point) FROM Tier t JOIN t.member m ON m = :member JOIN t.music mi ON mi.keyType = :keyType")
  Double findSumPointByMemberAndKeyType(Member member, KeyType keyType);
}
