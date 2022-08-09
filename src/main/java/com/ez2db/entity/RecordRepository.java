package com.ez2db.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordRepository
{
  private final EntityManager em;

  public Record findById(Long id)
  {
    return em.find(Record.class, id);
  }

  /**
   * 전체 기록 조회
   * @return
   */
  public List<Record> findByUserId(String userId)
  {
    return em.createQuery("SELECT r FROM Record r WHERE r.member.userId = :userId ORDER BY r.addTime DESC", Record.class)
      .setParameter("userId", userId)
      .getResultList();
  }

  public List<Record> findByUserIdWithKeyTypeWithLevel(String userId, KeyType keyType, int level)
  {
    return em.createQuery("SELECT r FROM Record r WHERE r.member.userId = :userId AND r.music.level = :level AND r.music.keyType = :keyType ORDER BY r.music.rank DESC, r.addTime DESC", Record.class)
      .setParameter("userId", userId)
      .setParameter("level", level)
      .setParameter("keyType", keyType)
      .getResultList();
  }
}
