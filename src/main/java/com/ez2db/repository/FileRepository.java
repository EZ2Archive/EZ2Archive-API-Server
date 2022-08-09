package com.ez2db.repository;

import com.ez2db.entity.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileRepository
{
  private final EntityManager em;

  public Long save(File file)
  {
    em.persist(file);

    return file.getId();
  }

  public Optional<File> findByFileName(String fileName)
  {
    Optional<File> file = Optional.empty();

    try
    {
      file = Optional.ofNullable(
        em.createQuery("SELECT f FROM File f WHERE f.fileName = :fileName", File.class)
          .setParameter("fileName", fileName)
          .getSingleResult()
      );
    }
    catch( NoResultException e )
    {
      // ignore
    }

    return file;
  }
}
