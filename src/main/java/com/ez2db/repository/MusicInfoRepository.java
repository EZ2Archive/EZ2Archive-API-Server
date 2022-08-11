package com.ez2db.repository;

import com.ez2db.entity.KeyType;
import com.ez2db.entity.MusicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MusicInfoRepository
{
  private final EntityManager em;

  public Optional<MusicInfo> findById(Long id)
  {
    Optional<MusicInfo> findMusicInfo = Optional.empty();
    try
    {
      findMusicInfo = Optional.ofNullable( em.createQuery("SELECT m FROM MusicInfo m WHERE m.id = :id", MusicInfo.class)
        .setParameter("id", id)
        .getSingleResult() );
    }
    catch( NoResultException e )
    {
      // ignore
    }
    return findMusicInfo;
  }

  public Long findMusicInfoCountByKeyTypeWithLevel(KeyType keyType, int level)
  {
    return em.createQuery("SELECT count(mi) FROM MusicInfo as mi WHERE mi.keyType = :keyType AND mi.level = :level", Long.class)
      .setParameter("keyType", keyType)
      .setParameter("level", level)
      .getSingleResult();
  }
}
