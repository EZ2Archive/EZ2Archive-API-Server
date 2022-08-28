package com.ez2archive.repository;

import com.ez2archive.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>
{
  Page<Notice> findNoticesByTitleContainingOrderByAddTimeDesc(String title, Pageable pageable);
}
