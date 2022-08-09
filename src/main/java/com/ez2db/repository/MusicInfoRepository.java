package com.ez2db.repository;

import com.ez2db.entity.MusicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MusicInfoRepository
{
  private final EntityManager em;

  public Long save(MusicInfo musicInfo)
  {
    em.persist(musicInfo);

    return musicInfo.getId();
  }

  public List<MusicInfo> findAll()
  {
    return em.createQuery("SELECT m FROM MusicInfo m ORDER BY m.addTime DESC", MusicInfo.class)
      .getResultList();
  }

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

  public Long delete(MusicInfo musicInfo)
  {
    em.remove(musicInfo);

    return musicInfo.getId();
  }
}
