package com.ez2archive.service;

import com.ez2archive.common.exception.business.ResourceNotFoundException;
import com.ez2archive.entity.Notice;
import com.ez2archive.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    return noticeRepository.findById(id)
      .orElseThrow( () -> new ResourceNotFoundException("공지 정보가 존재하지 않습니다.") );
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
