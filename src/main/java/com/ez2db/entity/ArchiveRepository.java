package com.ez2db.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArchiveRepository
{
  private final EntityManager em;

  public List<AchieveVO> findByUserIdWithKeyTypeWithLevel(String userId, KeyType keyType, int level)
  {
    String qString = "SELECT NEW com.ez2db.entity.AchieveVO(r.id, mi.name, r.score, mi.level, mi.rank, r.grade, mi.imageFile) " +
      "FROM Record r JOIN r.music mi JOIN r.member m " +
      "WHERE m.userId     = :userId " +
      "AND   mi.keyType   = :keyType " +
      "AND   mi.level     = :level  ";

    return em.createQuery(qString, AchieveVO.class)
      .setParameter("userId", userId)
      .setParameter("keyType", keyType)
      .setParameter("level", level)
      .getResultList();
  }
}
