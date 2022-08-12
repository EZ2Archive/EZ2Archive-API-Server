package com.ez2db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 기록 엔티티 클래스
 */
@Entity
@Getter @Setter
public class Record
{
  /** 기록 아이디 */
  @Id @GeneratedValue
  @Column(name = "record_id")
  private Long id;

  /** 음원 정보 */
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "music_info_id")
  private MusicInfo music;

  /** 사용자 정보 */
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "record_detail_id", nullable = false)
  private RecordDetail recordDetail;

  @Column(nullable = false)
  private LocalDateTime addTime;

}
