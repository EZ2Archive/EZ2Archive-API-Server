package com.ez2db.repository;

import com.ez2db.entity.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordRepository
{
  private final EntityManager em;

  public Long save(Record record)
  {
    em.persist(record);

    return record.getId();
  }
}
