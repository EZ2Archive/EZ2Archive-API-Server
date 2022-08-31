package com.ez2archive.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class TierPoint
{
  @Id @GeneratedValue
  @Column(name = "tier_point_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "music_info_id", nullable = false)
  private MusicInfo music;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @OneToOne(mappedBy = "tierPoint", fetch = FetchType.LAZY)
  @JoinColumn(name = "record_id")
  private Record record;

  private double point;

  @Column(nullable = false)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime addTime;

  public TierPoint()
  {
  }

  @Builder
  public TierPoint(Long id, MusicInfo music, Member member, Record record, double point, LocalDateTime addTime)
  {
    this.id = id;
    this.music = music;
    this.member = member;
    this.record = record;
    this.point = point;
    this.addTime = addTime;
  }
}
