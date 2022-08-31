package com.ez2archive.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
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
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "music_info_id")
  private MusicInfo music;

  /** 사용자 정보 */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tier_point_id")
  private TierPoint tierPoint;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "record_detail_id", nullable = false)
  private RecordDetail recordDetail;

  @Column(nullable = false)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime addTime;

  public Record()
  {
  }

  @Builder
  public Record(Long id, MusicInfo music, Member member, RecordDetail recordDetail, LocalDateTime addTime)
  {
    this.id = id;
    this.music = music;
    this.member = member;
    this.recordDetail = recordDetail;
    this.addTime = addTime;
  }
}
