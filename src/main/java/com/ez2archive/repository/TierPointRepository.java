package com.ez2archive.repository;

import com.ez2archive.dto.tier.TierPointMaxDTO;
import com.ez2archive.entity.KeyType;
import com.ez2archive.entity.Member;
import com.ez2archive.entity.TierPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TierPointRepository extends JpaRepository<TierPoint, Long>
{
  @EntityGraph(attributePaths = {"member", "music"})
  List<TierPoint> findTierPointsByMemberAndMusicKeyType(Member member, KeyType keyType);

  @Query("SELECT new com.ez2archive.dto.tier.TierPointMaxDTO(m.id, mi.id, max(tp.point)) FROM TierPoint tp JOIN tp.member m ON m = :member JOIN tp.music mi ON mi.keyType = :keyType GROUP BY m.id, mi.id ORDER BY max(tp.point) DESC")
  List<TierPointMaxDTO> findMaxPointsByMemberOrderByPointDesc(@Param("member") Member member, @Param("keyType") KeyType keyType, Pageable pageable);

}
