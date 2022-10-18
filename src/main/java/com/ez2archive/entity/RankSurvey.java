package com.ez2archive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class RankSurvey
{
  @Id @GeneratedValue
  @Column(name = "rank_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "music_id")
  private MusicInfo music;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  private int point;

  @JsonIgnore
  private LocalDateTime addTime;

  @JsonIgnore
  private LocalDateTime lastModifyTime;

  public RankSurvey()
  {
  }

  @Builder
  public RankSurvey(Long id, MusicInfo music, Member member, int point, LocalDateTime addTime, LocalDateTime lastModifyTime)
  {
    this.id = id;
    this.music = music;
    this.member = member;
    this.point = point;
    this.addTime = addTime;
    this.lastModifyTime = lastModifyTime;
  }
}
