package com.ez2db.service;

import com.ez2db.common.exception.business.ResourceNotFoundException;
import com.ez2db.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService
{
  private final NoticeRepository noticeRepository;

  public Page<Notice> findNoticesByTitle(String title, Pageable pageable)
  {
    return noticeRepository.findNoticesByTitleContainingOrderByAddTimeDesc(title, pageable);
  }

  public Notice findNoticeById(Long id)
  {
    Optional<Notice> findNotice = noticeRepository.findById(id);

    if ( findNotice.isEmpty() ) throw new ResourceNotFoundException("공지 정보가 존재하지 않습니다.");

    return findNotice.get();
  }

  @Transactional
  public Long save(Notice notice)
  {
    notice.setId(null);
    notice.setAddTime(LocalDateTime.now());

    noticeRepository.save(notice);

    return notice.getId();
  }

  @Transactional
  public void update(Notice notice)
  {
    Notice findNotice = this.findNoticeById(notice.getId());

    findNotice.setTitle(notice.getTitle());
    findNotice.setContent(notice.getContent());
  }

  @Transactional
  public void delete(Long id)
  {
    Notice findNotice = this.findNoticeById(id);

    noticeRepository.delete(findNotice);
  }

}
